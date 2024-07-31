package com.tbmob.m_business.presentation.user.home

import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser

data class HomeState(
    val sales: List<SalesWithProductAndUser> = emptyList(),
    val products: List<Product> = emptyList(),
    val saleToSaveName: String = "",
    val saleToSaveQuantity: Int = 0
)
