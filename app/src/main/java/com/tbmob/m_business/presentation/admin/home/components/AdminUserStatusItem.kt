package com.tbmob.m_business.presentation.admin.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.presentation.common.RowInfo

@Composable
fun AdminUserStatusItem(
    modifier: Modifier = Modifier,
    username: String,
    sold: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .widthIn(max = 240.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            RowInfo(title = "Username:", value = username)
            Spacer(modifier = Modifier.height(8.dp))
            RowInfo(title = "Day Sold:", value = "$sold Gnf")
        }
    }
}