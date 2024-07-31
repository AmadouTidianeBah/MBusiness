package com.tbmob.m_business.presentation.admin.user.add_user

data class CreateUserState(
    val fullName: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isAdmin: Boolean = false,
    val hasError: Boolean = false
)
