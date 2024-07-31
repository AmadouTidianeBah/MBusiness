package com.tbmob.m_business.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val fullName: String,
    @PrimaryKey
    val username: String,
    val password: String,
    val admin: Boolean
)
