package com.tbmob.m_business.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tbmob.m_business.data.local.dao.ProductDao
import com.tbmob.m_business.data.local.dao.SalesDao
import com.tbmob.m_business.data.local.dao.UserDao
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.data.local.entities.Sales
import com.tbmob.m_business.data.local.entities.User

@Database(
    entities = [User::class, Product::class, Sales::class],
    version = 1,
    exportSchema = false
)
abstract class MBusinessDatabase: RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val userDao: UserDao
    abstract val salesDao: SalesDao

    companion object {
        const val NAME = "m_ebusiness_db"
    }
}