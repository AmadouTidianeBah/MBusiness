package com.tbmob.m_business.presentation.user.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbmob.m_business.core.dateFormater
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.common.ProductImage
import com.tbmob.m_business.presentation.common.RowInfo
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    item: SalesWithProductAndUser,
    onItemClicked: (SalesWithProductAndUser) -> Unit
) {

    Card(
        modifier = modifier
            .heightIn(min = 76.dp)
            .clickable { onItemClicked(item) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            ProductImage(productImage = item.product.image)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = item.product.name,
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                    overflow = TextOverflow.Clip
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Sold: ${item.sales.quantity}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.Red.copy(alpha = .5f)
                    )
                    Text(
                        text = "Remaining: ${item.product.quantity - item.sales.quantity}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                RowInfo(title = "Time:", value = dateFormater(item.sales.dateSold))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeItemPre() {
    MBusinessTheme {
//        HomeItem()
    }
}