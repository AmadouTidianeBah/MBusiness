package com.tbmob.m_business.data.repository

import com.tbmob.m_business.data.local.dao.SalesDao
import com.tbmob.m_business.data.local.entities.Sales
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.domain.repository.SalesRepository
import kotlinx.coroutines.flow.Flow

class SalesRepositoryImpl(
    private val salesDao: SalesDao
) : SalesRepository {
    override suspend fun upsertSale(sales: Sales) {
        salesDao.upsertSale(sales)
    }

    override suspend fun deleteSale(sales: Sales) {
        salesDao.deleteSale(sales)
    }

    override fun getAllSales(): Flow<List<SalesWithProductAndUser>> {
        return salesDao.getAllSales()
    }

    override fun getSalesByUsername(username: String): Flow<List<SalesWithProductAndUser>> {
        return salesDao.getSalesByUsername(username)
    }
}