package com.tbmob.m_business.presentation.user.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme
import com.tbmob.m_business.presentation.user.detail.components.ItemDetail
import com.tbmob.m_business.presentation.user.detail.components.ItemDetailSold

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    sales: List<SalesWithProductAndUser>,
    sold: Int,
    remaining: Int,
    sale: SalesWithProductAndUser
) {
    Scaffold(
        modifier = modifier
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            ItemDetail(
                sale = sale,
                sold = sold,
                remaining = remaining
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "All Sold",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            LazyColumn {
                items(items = sales, key = {it.sales.id}) {sale ->
                    ItemDetailSold(sold = sale.sales.quantity, date = sale.sales.dateSold)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailScreenPre() {
    MBusinessTheme {
//        DetailScreen()
    }
}