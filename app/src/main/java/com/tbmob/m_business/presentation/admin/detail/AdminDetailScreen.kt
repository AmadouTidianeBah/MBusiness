package com.tbmob.m_business.presentation.admin.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.User
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.admin.common.AdminProductItem
import com.tbmob.m_business.presentation.admin.detail.components.AdminUserInfo
import com.tbmob.m_business.presentation.common.SoldAndFilterRow
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDetailScreen(
    modifier: Modifier = Modifier,
    user: User,
    sales: List<SalesWithProductAndUser>,
    amount: Double,
    onSortedByNameClicked: () -> Unit,
    onSortedByDateClicked: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        AdminUserInfo(
            user = user,
            amount = amount,
            modifier = Modifier.padding(12.dp)
        )
        SoldAndFilterRow(
            onNameClicked = onSortedByNameClicked,
            onDateClicked = onSortedByDateClicked
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            items(items = sales, key = {it.sales.id}) {sold ->
                AdminProductItem(soldProduct = sold)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminDetailPre() {
    MBusinessTheme {
//        AdminDetailScreen()
    }
}