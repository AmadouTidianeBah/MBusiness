package com.tbmob.m_business.core

sealed class Resources<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>: Resources<T>()
    class Success<T>(data: T): Resources<T>(data)
    class Error<T>(message: String): Resources<T>(message = message)
}
