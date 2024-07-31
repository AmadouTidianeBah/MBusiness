package com.tbmob.m_business.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateFormater(long: Long, pattern: String = "HH:mm:ss\ndd/MM/yyyy"): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    val date = Date(long)

    return sdf.format(date)
}