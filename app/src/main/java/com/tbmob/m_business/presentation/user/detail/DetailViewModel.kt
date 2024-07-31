package com.tbmob.m_business.presentation.user.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.data.repository.MBusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MBusinessRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _sales: MutableStateFlow<List<SalesWithProductAndUser>?> = MutableStateFlow(null)
    val sales = _sales.asStateFlow()
    private var _productSoldQuantity = MutableStateFlow(0)
    val productSoldQuantity = _productSoldQuantity.asStateFlow()
    private var _remaining = MutableStateFlow(0)
    val remaining = _remaining.asStateFlow()
    private var _username by mutableStateOf("")

    init {
        savedStateHandle.get<String>("username")?.let { username -> _username = username }
        savedStateHandle.get<String>("salesId")?.let { productName ->
            viewModelScope.launch {
                val salesDB = repository.salesRepository.getAllSales().firstOrNull()

                if (salesDB == null) _sales.value = null
                else {
                    _sales.value = salesDB.filter { it.user.username == _username }.filter { it.product.name == productName }
                    sales.value?.forEach { sale ->
                        _productSoldQuantity.value += sale.sales.quantity
                    }
                    _remaining.value = sales.value?.first()?.product?.quantity!! - productSoldQuantity.value
                }
            }
        }
    }
}