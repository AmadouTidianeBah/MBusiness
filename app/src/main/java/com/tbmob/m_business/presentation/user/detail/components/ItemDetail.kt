package com.tbmob.m_business.presentation.user.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.common.ProductImage
import com.tbmob.m_business.presentation.common.RowInfo

@Composable
fun ItemDetail(
    modifier: Modifier = Modifier,
    sale: SalesWithProductAndUser,
    remaining: Int,
    sold: Int
) {
    Card(
        modifier = modifier
            .heightIn(min = 76.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ProductImage(productImage = sale.product.image)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = sale.product.name,
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    overflow = TextOverflow.Clip
                )
                Spacer(modifier = Modifier.height(12.dp))
                RowInfo(title = "Total", value = sale.product.quantity.toString())
                Spacer(modifier = Modifier.height(12.dp))
                RowInfo(title = "Remaining", value = remaining.toString())
                Spacer(modifier = Modifier.height(12.dp))
                RowInfo(title = "Sold", value = sold.toString())
            }
        }
    }
}