package com.tbmob.m_business.presentation.admin.detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.core.formatNumber
import com.tbmob.m_business.data.local.entities.User
import com.tbmob.m_business.presentation.common.RowInfo

@SuppressLint("DefaultLocale")
@Composable
fun AdminUserInfo(
    modifier: Modifier = Modifier,
    user: User,
    amount: Double
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            RowInfo(title = "Name:", value = user.fullName)
            Spacer(modifier = Modifier.height(8.dp))
            RowInfo(title = "Username:", value = user.username)
            Spacer(modifier = Modifier.height(8.dp))
            RowInfo(title = "Day Sold:", value = formatNumber(amount) + " $")
        }
    }
}