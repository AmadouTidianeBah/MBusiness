package com.tbmob.m_business.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.data.local.entities.Sales
import com.tbmob.m_business.data.local.entities.User

data class SalesWithProductAndUser(
    @Embedded val sales: Sales,
    @Relation(
        parentColumn = "productId",
        entityColumn = "name"
    )
    val product: Product,
    @Relation(
        parentColumn = "userId",
        entityColumn = "username"
    )
    val user: User
)

