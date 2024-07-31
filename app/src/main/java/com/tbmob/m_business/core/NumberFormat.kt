package com.tbmob.m_business.core

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun formatNumber(number: Double, decimalPlaces: Int = 2): String {
    // Define the pattern based on the required decimal places
    val pattern = "#,###." + "#".repeat(decimalPlaces)
    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = '.'
        decimalSeparator = ','
    }

    // Create the DecimalFormat instance with the specified pattern and symbols
    val decimalFormat = DecimalFormat(pattern, symbols)

    // Format the number and return the result
    return decimalFormat.format(number)
}