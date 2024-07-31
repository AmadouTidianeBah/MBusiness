package com.tbmob.m_business.presentation.admin.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbmob.m_business.data.local.entities.User
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
class HomeUserViewModel @Inject constructor(
    private val repository: MBusinessRepository
): ViewModel() {
    private var _users: MutableStateFlow<List<User>?> = MutableStateFlow(null)
    val users = _users.asStateFlow()
    private var _userDelete: MutableStateFlow<User?> = MutableStateFlow(null)
    val userDelete = _userDelete.asStateFlow()
    private var job: Job? = null

    init {
        getUsers()
    }

    private fun getUsers() {
        job?.cancel()
        job = repository.userRepository.getAllUsers().onEach { users ->
            if (users.isEmpty()) _users.value = null
            else _users.value = users
        }.launchIn(viewModelScope)
    }

    fun restoreProduct() {
        viewModelScope.launch {
            userDelete.value?.let { repository.userRepository.upsertUser(it) }
        }
        _userDelete.value = null
    }

    fun deleteProduct(user: User) {
        viewModelScope.launch {
            repository.userRepository.deleteUser(user)
        }
        _userDelete.value = user
    }
}