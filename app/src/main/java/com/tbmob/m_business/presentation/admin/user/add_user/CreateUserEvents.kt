package com.tbmob.m_business.presentation.admin.user.add_user

sealed class CreateUserEvents {
    data class OnFullNameChange(val fullName: String): CreateUserEvents()
    data class OnUsernameChange(val username: String): CreateUserEvents()
    data class OnPasswordChange(val password: String): CreateUserEvents()
    data class OnConfirmPasswordChange(val confirmPassword: String): CreateUserEvents()
    data class OnAdminChecked(val isAdmin: Boolean): CreateUserEvents()
    data object OnCreateUserClicked: CreateUserEvents()
}