package com.tbmob.m_business.presentation.admin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.data.repository.MBusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor(
    private val repository: MBusinessRepository
): ViewModel() {
    private var _sales: MutableStateFlow<List<SalesWithProductAndUser>?> = MutableStateFlow(null)
    val sales = _sales.asStateFlow()
    private var _amount: MutableStateFlow<MutableMap<String, Double>> = MutableStateFlow(
        mutableMapOf()
    )
    val amount = _amount.asStateFlow()
    private var _usersAndSold: MutableStateFlow<List<SalesWithProductAndUser>?> = MutableStateFlow(null)
    val usersAndSold = _usersAndSold.asStateFlow()
    private var job: Job? = null
    private var jobUser: Job? = null

    init {
        getAllSales()
        getUsersAndSold()
    }

    private fun getAllSales() {
        job?.cancel()
        job = repository.salesRepository.getAllSales().onEach { sales ->
            if (sales.isEmpty()) _sales.value = null
            else _sales.value = sales
        }.launchIn(viewModelScope)
    }

    private fun getUsersAndSold() {
        jobUser?.cancel()
        jobUser = repository.salesRepository.getAllSales().onEach {usersFromDB ->
            if (usersFromDB.isEmpty()) _usersAndSold.value = null
            else {
                _usersAndSold.value = usersFromDB.filter { !it.user.admin }
                val mapData = mutableMapOf<String, Double>()
                usersAndSold.value?.forEach {userAndSold ->
                    if (mapData.containsKey(userAndSold.user.username)) {
                        mapData[userAndSold.user.username] = mapData[userAndSold.user.username]!! + (userAndSold.product.sellPrice * userAndSold.sales.quantity)
                    } else mapData[userAndSold.user.username] = userAndSold.product.sellPrice * userAndSold.sales.quantity
                }
                _amount.value = mapData
            }
        }.launchIn(viewModelScope)
    }

    fun sortedBy(sortedBy: SortedBy) {
        when(sortedBy) {
            SortedBy.SortedByDate -> _sales.value = sales.value?.sortedBy { it.sales.dateSold }?.reversed()
            SortedBy.SortedByName -> _sales.value = sales.value?.sortedBy { it.product.name }
        }
    }

    sealed class SortedBy {
        data object SortedByName: SortedBy()
        data object SortedByDate: SortedBy()
    }
}