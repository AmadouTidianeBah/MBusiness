package com.tbmob.m_business.presentation.user.home_sell

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbmob.m_business.data.local.entities.Sales
import com.tbmob.m_business.data.repository.MBusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellViewModel @Inject constructor(
    private val repository: MBusinessRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _state = MutableStateFlow(SellState())
    val state = _state.asStateFlow()


    init {
        savedStateHandle.get<String>("username")?.let { username ->
            _state.update { it.copy(username = username) }
        }
        getAllProducts()
        getProductSoldQuantity()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            val products = repository.productRepository.getAllProducts().firstOrNull()
            _state.update { it.copy(products = products ?: emptyList()) }
        }
    }

    private fun getProductSoldQuantity() {
        viewModelScope.launch {
            val sales = repository.salesRepository.getAllSales().firstOrNull()

            if (sales == null) _state.update { it.copy(soldQuantity = mutableMapOf()) }
            else {
                val quantityMap = mutableMapOf<String, Int>()

                sales.forEach {sale ->
                    if (quantityMap.containsKey(sale.product.name)) {
                        quantityMap[sale.product.name] = quantityMap[sale.product.name]!! + sale.sales.quantity
                    } else quantityMap[sale.product.name] = sale.sales.quantity
                }

                _state.update { it.copy(soldQuantity = quantityMap) }
            }
        }
    }

    fun saveSale() {
        viewModelScope.launch {
            repository.salesRepository.upsertSale(
                Sales(
                    productId = state.value.saleToSaveName,
                    quantity = state.value.saleToSaveQuantity,
                    userId = state.value.username,
                    dateSold = System.currentTimeMillis()
                )
            )
        }
    }

    fun isSellValid(): Boolean = state.value.saleToSaveName.isNotBlank()
            && state.value.saleToSaveQuantity != 0

    fun updateSaleToSaveName(name: String) {
        _state.update { it.copy(saleToSaveName = name) }
    }

    fun updateSaleToSaveQuantity(quantity: Int) {
        _state.update { it.copy(saleToSaveQuantity = quantity) }
    }

    fun updateRemaining(remaining: Int) {
        _state.update { it.copy(remaining = remaining) }
    }
}