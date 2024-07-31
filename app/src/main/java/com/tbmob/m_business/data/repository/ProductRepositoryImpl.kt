package com.tbmob.m_business.data.repository

import com.tbmob.m_business.data.local.dao.ProductDao
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun upsertProduct(product: Product) {
        productDao.upsertProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    override fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts()
    }

    override fun getProductByName(name: String): Flow<Product> {
        return productDao.getProductByName(name)
    }
}