package com.tbmob.m_business.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val name: String,
    val quantity: Int,
    val basePrice: Double,
    val sellPrice: Double,
    val image: ByteArray?
)
