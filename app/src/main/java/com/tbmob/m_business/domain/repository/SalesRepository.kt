package com.tbmob.m_business.domain.repository

import com.tbmob.m_business.data.local.entities.Sales
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import kotlinx.coroutines.flow.Flow

interface SalesRepository {
    suspend fun upsertSale(sales: Sales)
    suspend fun deleteSale(sales: Sales)
    fun getAllSales(): Flow<List<SalesWithProductAndUser>>
    fun getSalesByUsername(username: String): Flow<List<SalesWithProductAndUser>>
}