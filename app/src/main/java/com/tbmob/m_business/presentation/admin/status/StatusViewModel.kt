package com.tbmob.m_business.presentation.admin.status

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
class StatusViewModel @Inject constructor(
    private val repository: MBusinessRepository
): ViewModel() {
    private var _employees: MutableStateFlow<List<SalesWithProductAndUser>?> = MutableStateFlow(null)
    val employees = _employees.asStateFlow()
    private var _sold: MutableStateFlow<Double> = MutableStateFlow(.0)
    val sold = _sold.asStateFlow()
    private var _expended: MutableStateFlow<Double> = MutableStateFlow(.0)
    val expended = _expended.asStateFlow()
    private var _benefit: MutableStateFlow<Double> = MutableStateFlow(.0)
    val benefit = _benefit.asStateFlow()
    private var _userSold: MutableStateFlow<MutableMap<String, Double>?> = MutableStateFlow(null)
    val userSold = _userSold.asStateFlow()
    private var _date: MutableStateFlow<Long> = MutableStateFlow(System.currentTimeMillis())
    val date = _date.asStateFlow()

    init {
        getUsersAndSold()
    }

    fun updateDate(newDate: Long) {
        if (newDate > System.currentTimeMillis()) _date.value = System.currentTimeMillis()
        else {
            _date.value = newDate
            val mapData = mutableMapOf<String, Double>()

            employees.value?.forEach {userAndSold ->
                if (date.value <= userAndSold.sales.dateSold) {
                    if (mapData.containsKey(userAndSold.user.username)) {
                        mapData[userAndSold.user.username] = mapData[userAndSold.user.username]!! + (userAndSold.product.sellPrice * userAndSold.sales.quantity)
                    } else {
                        mapData[userAndSold.user.username] =
                            userAndSold.product.sellPrice * userAndSold.sales.quantity
                    }
                }
            }

            if (mapData.isNotEmpty()) {
                resetUserSoldAndSold()
                _userSold.value = mapData
                userSold.value?.values?.forEach {
                    _sold.value += it
                }
                _benefit.value = sold.value - expended.value
            } else {
                resetUserSoldAndSold()
                getUsersAndSold()
            }
        }
    }

    private fun getUsersAndSold() {
        viewModelScope.launch {
            val usersFromDB = repository.salesRepository.getAllSales().firstOrNull()

            if (usersFromDB == null) _employees.value = null
            else {
                _employees.value = usersFromDB.filter { !it.user.admin }
                val userSoldMap = mutableMapOf<String, Double>()
                val expendMap = mutableMapOf<String, MutableMap<Double, Int>>()

                employees.value?.forEach {userAndSold ->
                    if (!expendMap.containsKey(userAndSold.product.name)) {
                         expendMap[userAndSold.product.name] = mutableMapOf(Pair(userAndSold.product.basePrice, userAndSold.product.quantity))
                    }

                    if (userSoldMap.containsKey(userAndSold.user.username)) {
                        userSoldMap[userAndSold.user.username] = userSoldMap[userAndSold.user.username]!! + (userAndSold.product.sellPrice * userAndSold.sales.quantity)
                    } else {
                        userSoldMap[userAndSold.user.username] =
                            userAndSold.product.sellPrice * userAndSold.sales.quantity
                    }
                }

                expendMap.forEach { (_, value) ->
                    value.forEach { (key, value) ->
                        _expended.value += (key * value)
                    }
                }

                _userSold.value = userSoldMap
                userSold.value?.values?.forEach {
                    _sold.value += it
                }

                _benefit.value = sold.value - expended.value
            }
        }
    }

    private fun resetUserSoldAndSold() {
        _userSold.value = null
        _sold.value = 0.0
    }
}