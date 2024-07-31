package com.tbmob.m_business.data.repository

import com.tbmob.m_business.data.local.dao.UserDao
import com.tbmob.m_business.data.local.entities.User
import com.tbmob.m_business.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun upsertUser(user: User) {
        userDao.upsertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    override fun getUserByUsername(username: String): Flow<User> {
        return userDao.getUserByUsername(username)
    }

    override fun getUserByUsernameAndPassword(username: String, password: String): Flow<User> {
        return userDao.getUserByUsernameAndPassword(username, password)
    }
}