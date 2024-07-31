package com.tbmob.m_business.presentation.user.home_sell

import com.tbmob.m_business.data.local.entities.Product

data class SellState(
    val products: List<Product> = emptyList(),
    val username: String = "",
    val soldQuantity: MutableMap<String, Int> = mutableMapOf(),
    val remaining: Int = 0,
    val saleToSaveName: String = "",
    val saleToSaveQuantity: Int = 0,
)
