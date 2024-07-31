package com.tbmob.m_business.presentation.auth.login

sealed class LoginEvents {
    data class OnUsernameChange(val username: String): LoginEvents()
    data class OnPasswordChange(val password: String): LoginEvents()
}