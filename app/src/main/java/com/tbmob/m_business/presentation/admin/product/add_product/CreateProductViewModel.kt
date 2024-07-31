package com.tbmob.m_business.presentation.admin.product.add_product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.data.repository.MBusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProductViewModel @Inject constructor(
    private val repository: MBusinessRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _state = MutableStateFlow(CreateProductState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("productName")?.let {productName ->
            viewModelScope.launch {
                val product =
                    repository.productRepository.getProductByName(productName).firstOrNull()

                if (product != null) {
                    _state.update {
                        it.copy(
                            name = product.name,
                            image = product.image,
                            quantity = product.quantity.toString(),
                            sellPrice = product.sellPrice.toString(),
                            basePrice = product.basePrice.toString()
                        )
                    }
                }
            }
        }
    }

    fun onEvent(events: CreateProductEvents) {
        when(events) {
            is CreateProductEvents.OnNameChange -> _state.update { it.copy(name = events.name) }
            is CreateProductEvents.OnBasePriceChange -> _state.update { it.copy(basePrice = events.price) }
            is CreateProductEvents.OnSellPriceChange -> _state.update { it.copy(sellPrice = events.price) }
            is CreateProductEvents.OnQuantityChange -> _state.update { it.copy(quantity = events.quantity) }
            is CreateProductEvents.OnImageChange -> _state.update { it.copy(image = events.image) }
            is CreateProductEvents.OnCreateProductClicked -> {
                viewModelScope.launch {
                    repository.productRepository.upsertProduct(
                        Product(
                            name = state.value.name,
                            image = state.value.image,
                            quantity = state.value.quantity.toInt(),
                            sellPrice = state.value.sellPrice.toDouble(),
                            basePrice = state.value.basePrice.toDouble()
                        )
                    )
                }
            }

        }
    }

    fun areFieldsBlank(): Boolean {
        return state.value.name.isNotBlank() && state.value.sellPrice.isNotBlank()
                && state.value.quantity.isNotBlank() && state.value.basePrice.isNotBlank()
    }
}