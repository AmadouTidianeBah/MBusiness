package com.tbmob.m_business.presentation.admin.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.core.formatNumber
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@SuppressLint("DefaultLocale")
@Composable
fun AdminStatusHome(
    modifier: Modifier = Modifier,
    usersAndSold: List<SalesWithProductAndUser>,
    amount: Map<String, Double>,
    onUserStatusClick: (SalesWithProductAndUser) -> Unit
) {
    LazyRow(
        modifier = modifier
    ) {
        items(items = usersAndSold, key = {it.sales.id}) { userAndSold ->
            AdminUserStatusItem(
                username = userAndSold.user.fullName,
                sold = formatNumber(amount[userAndSold.user.username] ?: 0.0),
                onClick = { onUserStatusClick(userAndSold) }
            )
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminStatusPre() {
    MBusinessTheme {
//        AdminStatusHome()
    }
}