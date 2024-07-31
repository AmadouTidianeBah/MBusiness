package com.tbmob.m_business.presentation.user.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.core.dateFormater
import com.tbmob.m_business.presentation.common.RowInfo

@Composable
fun ItemDetailSold(
    modifier: Modifier = Modifier,
    sold: Int,
    date: Long
) {
    Card(
        modifier = modifier
    ) {
        RowInfo(title = sold.toString(), value = dateFormater(date), modifier = Modifier.padding(12.dp))
    }
}