package com.tbmob.m_business.presentation.user.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser

@Composable
fun HomeSoldStatus(
    modifier: Modifier = Modifier,
    sales: List<SalesWithProductAndUser>,
    onItemClicked: (SalesWithProductAndUser) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = "Sold of the Day",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )

        LazyColumn {
            items(items = sales, key = {it.sales.id}) { item ->
                HomeItem(item = item, onItemClicked = onItemClicked)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}