package com.tbmob.m_business.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.R
import com.tbmob.m_business.presentation.admin.common.AdminDropdownIcon
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun SoldAndFilterRow(
    modifier: Modifier = Modifier,
    onNameClicked: () -> Unit,
    onDateClicked: () -> Unit
) {
    var isExpended by rememberSaveable { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = "Sold",
            style = MaterialTheme.typography.headlineLarge,
        )
        IconButton(onClick = { isExpended = !isExpended }) {
            AdminDropdownIcon(
                mainIconId = R.drawable.sort,
                isExpended = isExpended,
                onExpendedChange = { isExpended = it },
                firstItemText = "Name",
                firstItemIcon = Icons.Outlined.ShoppingCart,
                secondItemText = "Date",
                secondItemIcon = Icons.Outlined.DateRange,
                onFirstItemClick = onNameClicked,
                onSecondItemClick = onDateClicked
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SoldAndFilterPre() {
    MBusinessTheme {
//        SoldAndFilterRow()
    }
}