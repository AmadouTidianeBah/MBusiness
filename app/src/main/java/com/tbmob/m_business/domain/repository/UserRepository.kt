package com.tbmob.m_business.domain.repository

import com.tbmob.m_business.data.local.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun upsertUser(user: User)
    suspend fun deleteUser(user: User)
    fun getAllUsers(): Flow<List<User>>
    fun getUserByUsername(username: String): Flow<User>
    fun getUserByUsernameAndPassword(username: String, password: String): Flow<User>
}