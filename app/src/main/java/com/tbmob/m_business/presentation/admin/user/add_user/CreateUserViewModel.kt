package com.tbmob.m_business.presentation.admin.user.add_user

import androidx.lifecycle.SavedStateHandle
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
class CreateUserViewModel @Inject constructor(
    private val repository: MBusinessRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _state = MutableStateFlow(CreateUserState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("userId")?.let {userId ->
            viewModelScope.launch {
                val user = repository.userRepository.getUserByUsername(userId).firstOrNull()

                if (user != null) {
                    _state.update {
                        it.copy(
                            fullName = user.fullName,
                            username = user.username,
                            password = user.password,
                            isAdmin = user.admin
                        )
                    }
                }
            }
        }
    }

    fun onEvent(events: CreateUserEvents) {
        when(events) {
            is CreateUserEvents.OnFullNameChange -> _state.update { it.copy(fullName = events.fullName) }
            is CreateUserEvents.OnUsernameChange -> _state.update { it.copy(username = events.username) }
            is CreateUserEvents.OnPasswordChange -> _state.update { it.copy(password = events.password) }
            is CreateUserEvents.OnConfirmPasswordChange -> _state.update { it.copy(confirmPassword = events.confirmPassword) }
            is CreateUserEvents.OnAdminChecked -> _state.update { it.copy(isAdmin = events.isAdmin) }
            is CreateUserEvents.OnCreateUserClicked -> {
                if (state.value.password == state.value.confirmPassword) {
                    viewModelScope.launch {
                        repository.userRepository.upsertUser(
                            User(
                                fullName = state.value.fullName,
                                username = state.value.username,
                                password = state.value.password,
                                admin = state.value.isAdmin
                            )
                        )
                    }
                    _state.update { it.copy(hasError = false) }
                } else _state.update { it.copy(hasError = true) }
            }
        }
    }

    fun areFieldsBlank(): Boolean {
        return state.value.fullName.isNotBlank() && state.value.username.isNotBlank()
                && state.value.password.isNotBlank() && state.value.confirmPassword.isNotBlank()
                && state.value.password == state.value.confirmPassword
    }
}