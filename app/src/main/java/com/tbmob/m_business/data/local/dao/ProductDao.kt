package com.tbmob.m_business.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.tbmob.m_business.data.local.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Upsert
    suspend fun upsertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM PRODUCT")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT WHERE name=:name ")
    fun getProductByName(name: String): Flow<Product>
}