package com.tbmob.m_business.presentation.admin.detail

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
class AdminDetailViewModel @Inject constructor(
    private val repository: MBusinessRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _usersAndSold: MutableStateFlow<List<SalesWithProductAndUser>?> = MutableStateFlow(null)
    val usersAndSold = _usersAndSold.asStateFlow()
    private var _amount = MutableStateFlow(0.0)
    val amount = _amount.asStateFlow()

    init {
        savedStateHandle.get<String>("username")?.let {username ->
            getUserAndSold(username)
        }
    }

    private fun getUserAndSold(username: String) {
        viewModelScope.launch {
            val usersFromDB = repository.salesRepository.getSalesByUsername(username).firstOrNull()

            if (usersFromDB == null) _usersAndSold.value = null
            else {
                _usersAndSold.value = usersFromDB
                var tempAmount = .0
                usersAndSold.value?.forEach { sold ->
                    tempAmount += (sold.product.sellPrice * sold.sales.quantity)
                }
                _amount.value = tempAmount
            }
        }
    }

    fun sortedBy(sortedBy: SortedBy) {
        when(sortedBy) {
            SortedBy.SortedByDate -> _usersAndSold.value = usersAndSold.value?.sortedBy { it.sales.dateSold }?.reversed()
            SortedBy.SortedByName -> _usersAndSold.value = usersAndSold.value?.sortedBy { it.product.name }
        }
    }

    sealed class SortedBy {
        data object SortedByName: SortedBy()
        data object SortedByDate: SortedBy()
    }
}