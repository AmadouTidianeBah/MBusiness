package com.tbmob.m_business.presentation.admin.product.add_product

sealed class CreateProductEvents {
    data class OnNameChange(val name: String): CreateProductEvents()
    data class OnImageChange(val image: ByteArray?): CreateProductEvents()
    data class OnQuantityChange(val quantity: String): CreateProductEvents()
    data class OnSellPriceChange(val price: String): CreateProductEvents()
    data class OnBasePriceChange(val price: String): CreateProductEvents()
    data object OnCreateProductClicked: CreateProductEvents()
}