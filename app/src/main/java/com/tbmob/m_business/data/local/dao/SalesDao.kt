package com.tbmob.m_business.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.tbmob.m_business.data.local.entities.Sales
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import kotlinx.coroutines.flow.Flow

@Dao
interface SalesDao {
    @Upsert
    suspend fun upsertSale(sales: Sales)

    @Delete
    suspend fun deleteSale(sales: Sales)

    @Transaction
    @Query("SELECT * FROM SALES ORDER BY dateSold DESC")
    fun getAllSales(): Flow<List<SalesWithProductAndUser>>

    @Transaction
    @Query("""
        SELECT s.*
        FROM Sales s
        JOIN User u ON s.userId = u.username
        WHERE u.username = :username
        ORDER BY dateSold DESC
    """)
    fun getSalesByUsername(username: String): Flow<List<SalesWithProductAndUser>>
}