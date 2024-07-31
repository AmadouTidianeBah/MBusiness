package com.tbmob.m_business.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbmob.m_business.data.local.entities.User
import com.tbmob.m_business.data.repository.MBusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MBusinessRepository
): ViewModel() {
    private var _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(events: LoginEvents) {
        when(events) {
            is LoginEvents.OnUsernameChange -> _state.update { it.copy(username = events.username) }
            is LoginEvents.OnPasswordChange -> _state.update { it.copy(password = events.password) }
        }
    }

    fun login(username: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            try {
                val user = repository.userRepository.getUserByUsernameAndPassword(username, password).firstOrNull()
                onResult(user)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun areFieldsBlank(): Boolean {
        return state.value.username.isNotBlank() && state.value.password.isNotBlank()
    }
}