package com.tbmob.m_business.presentation.admin.status.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.core.formatNumber
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.common.RowInfo

@SuppressLint("DefaultLocale")
@Composable
fun UsersStatus(
    modifier: Modifier = Modifier,
    users: List<SalesWithProductAndUser>,
    userSold: Map<String, Double>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = users, key = {it.sales.id}) { item ->
            Card {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    RowInfo(title = "Name:", value = item.user.fullName)
                    Spacer(modifier = Modifier.height(8.dp))
                    RowInfo(title = "Username:", value = item.user.username)
                    Spacer(modifier = Modifier.height(8.dp))
                    RowInfo(title = "Post:", value = "Employee")
                    Spacer(modifier = Modifier.height(12.dp))
                    RowInfo(title = "Sold", value = formatNumber(userSold[item.user.username.lowercase()] ?: 0.00) + " $")
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}