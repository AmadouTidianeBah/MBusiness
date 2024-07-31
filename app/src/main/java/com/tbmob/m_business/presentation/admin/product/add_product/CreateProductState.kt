package com.tbmob.m_business.presentation.admin.product.add_product

data class CreateProductState(
    val name: String = "",
    val image: ByteArray? = null,
    val quantity: String = "",
    val basePrice: String = "",
    val sellPrice: String = "",
    val hasError: Boolean = false
)
