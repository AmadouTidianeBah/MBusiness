package com.tbmob.m_business.domain.repository

import com.tbmob.m_business.data.local.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun upsertProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    fun getAllProducts(): Flow<List<Product>>
    fun getProductByName(name: String): Flow<Product>
}