package com.tbmob.m_business

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {
    private var _userId = MutableStateFlow<String?>(null)
    val userId = _userId.asStateFlow()

    fun setupUserId(username: String) {
        _userId.value = username
    }
}