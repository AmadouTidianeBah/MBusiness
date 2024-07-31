package com.tbmob.m_business.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.tbmob.m_business.data.local.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM USER")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM USER WHERE username=:username ")
    fun getUserByUsername(username: String): Flow<User>

    @Query("SELECT * FROM USER WHERE username=:username AND password=:password")
    fun getUserByUsernameAndPassword(username: String, password: String): Flow<User>
}