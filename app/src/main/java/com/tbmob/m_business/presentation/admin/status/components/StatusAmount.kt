package com.tbmob.m_business.presentation.admin.status.components

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
import com.tbmob.m_business.presentation.common.RowInfo

@SuppressLint("DefaultLocale")
@Composable
fun StatusAmount(
    modifier: Modifier = Modifier,
    expend: Double,
    sold: Double,
    benefit: Double
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            RowInfo(title = "Expended:", value = formatNumber(expend) + " Gnf")
            Spacer(modifier = Modifier.height(12.dp))
            RowInfo(title = "Sold", value = formatNumber(sold) + " Gnf")
            Spacer(modifier = Modifier.height(12.dp))
            RowInfo(title = "Benefit", value = formatNumber(benefit) + " Gnf")
        }
    }
}