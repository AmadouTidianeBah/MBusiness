package com.tbmob.m_business.presentation.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbmob.m_business.data.repository.MBusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MBusinessRepository
): ViewModel() {
    private var _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private var _username = MutableStateFlow("")
    val username = _username.asStateFlow()
    private var _productSoldQuantity = MutableStateFlow(mutableMapOf<String, Int>())
    val productSoldQuantity = _productSoldQuantity.asStateFlow()
    private var _remaining = MutableStateFlow(0)
    val remaining = _remaining.asStateFlow()
    private var job: Job? = null
    private var jobProduct: Job? = null
    private var jobSold: Job? = null

    init {
        getAllProducts()
        getAllSales()
        getProductSoldQuantity()
    }

    fun updateUsername(username: String) {
        _username.value = username
    }

    private fun getAllProducts() {
        jobProduct?.cancel()
        jobProduct = repository.productRepository.getAllProducts().onEach { products ->
            if (products.isEmpty()) _state.update { it.copy(products = emptyList()) }
            else _state.update { it.copy(products = products) }
        }.launchIn(viewModelScope)
    }

    private fun getProductSoldQuantity() {
        jobSold?.cancel()
        job = repository.salesRepository.getAllSales().onEach { sales ->
            if (sales.isEmpty()) _productSoldQuantity.value = mutableMapOf()
            else {
                val quantityMap = mutableMapOf<String, Int>()

                sales.forEach {sale ->
                    if (quantityMap.containsKey(sale.product.name)) {
                        quantityMap[sale.product.name] = quantityMap[sale.product.name]!! + sale.sales.quantity
                    } else quantityMap[sale.product.name] = sale.sales.quantity
                }

                _productSoldQuantity.value = quantityMap
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllSales() {
        job?.cancel()
        job = repository.salesRepository.getAllSales().onEach { sales ->
            if (sales.isEmpty()) _state.update { it.copy(sales = emptyList()) }
            else {
                if (username.value.isNotBlank()) {
                    val filterSales = sales.filter { it.user.username == username.value }
                    _state.update { it.copy(sales = filterSales) }
                } else {
                    _state.update { it.copy(sales = sales) }
                }
            }
        }.launchIn(viewModelScope)
    }
}