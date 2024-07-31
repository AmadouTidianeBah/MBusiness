package com.tbmob.m_business.presentation.admin.product.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.data.repository.MBusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: MBusinessRepository
): ViewModel() {
    private var _products: MutableStateFlow<List<Product>?> = MutableStateFlow(null)
    val products = _products.asStateFlow()
    private var _productDelete: MutableStateFlow<Product?> = MutableStateFlow(null)
    val productDelete = _productDelete.asStateFlow()
    private var job: Job? = null

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        job?.cancel()
        job = repository.productRepository.getAllProducts().onEach { products ->
            if (products.isEmpty()) _products.value = null
            else _products.value = products
        }.launchIn(viewModelScope)
    }

    fun restoreProduct() {
        viewModelScope.launch {
            productDelete.value?.let { repository.productRepository.upsertProduct(it) }
        }
        _productDelete.value = null
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.productRepository.deleteProduct(product)
        }
        _productDelete.value = product
    }
}