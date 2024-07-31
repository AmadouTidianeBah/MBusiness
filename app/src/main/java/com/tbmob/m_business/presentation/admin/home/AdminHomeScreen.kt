package com.tbmob.m_business.presentation.admin.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.admin.common.AdminProductItem
import com.tbmob.m_business.presentation.admin.home.components.AdminStatusHome
import com.tbmob.m_business.presentation.common.SoldAndFilterRow
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun AdminHomeScreen(
    modifier: Modifier = Modifier,
    usersAndSold: List<SalesWithProductAndUser>,
    sales: List<SalesWithProductAndUser>,
    onUserStatusClick: (SalesWithProductAndUser) -> Unit,
    amount: Map<String, Double>,
    onSortedByNameClicked: () -> Unit,
    onSortedByDateClicked: () -> Unit,
) {

    Column(
        modifier = modifier
    ) {
        if (usersAndSold.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Users in database",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                )
            }
        } else {
            AdminStatusHome(
                modifier = Modifier.padding(top = 20.dp),
                usersAndSold = usersAndSold,
                amount = amount,
                onUserStatusClick = onUserStatusClick,
            )
        }
        SoldAndFilterRow(
            onNameClicked = onSortedByNameClicked,
            onDateClicked = onSortedByDateClicked,
        )

        if (sales.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Empty",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                items(items = sales, key = {it.sales.id}) {item ->
                    AdminProductItem(soldProduct = item)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminHomePre() {
    MBusinessTheme {
//        AdminHomeScreen()
    }
}