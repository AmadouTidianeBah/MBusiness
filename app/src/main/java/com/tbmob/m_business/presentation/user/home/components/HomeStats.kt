package com.tbmob.m_business.presentation.user.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.presentation.common.StatsItem
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun HomeStats(
    modifier: Modifier = Modifier,
    products: List<Product>,
    sold: Map<String, Int>
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Status",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(12.dp)
        )
        LazyRow(
            modifier = modifier
        ) {
            items(items = products, key = {it.name}) {product ->
                StatsItem(
                    soldIndicator = sold[product.name] ?: 0,
                    product = product
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeStatsPre() {
    MBusinessTheme {
//        HomeStats()
    }
}