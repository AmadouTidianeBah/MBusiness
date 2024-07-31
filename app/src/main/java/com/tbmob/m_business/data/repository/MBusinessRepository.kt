package com.tbmob.m_business.data.repository

import com.tbmob.m_business.domain.repository.ProductRepository
import com.tbmob.m_business.domain.repository.SalesRepository
import com.tbmob.m_business.domain.repository.UserRepository

data class MBusinessRepository(
    val productRepository: ProductRepository,
    val userRepository: UserRepository,
    val salesRepository: SalesRepository
)