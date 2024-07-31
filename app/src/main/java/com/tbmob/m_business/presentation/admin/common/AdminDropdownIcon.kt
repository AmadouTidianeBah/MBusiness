package com.tbmob.m_business.presentation.admin.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AdminDropdownIcon(
    modifier: Modifier = Modifier,
    mainIconId: Int,
    isExpended: Boolean,
    onExpendedChange: (Boolean) -> Unit,
    firstItemText: String,
    firstItemIcon: ImageVector,
    secondItemText: String,
    secondItemIcon: ImageVector,
    onFirstItemClick: () -> Unit,
    onSecondItemClick: () -> Unit,
) {

    Box(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = mainIconId),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )
        DropdownMenu(expanded = isExpended, onDismissRequest = { onExpendedChange(false) }) {
            DropdownMenuItem(
                text = { Text(firstItemText) },
                onClick = {
                    onExpendedChange(false)
                    onFirstItemClick()
                },
                leadingIcon = { Icon(firstItemIcon, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text(secondItemText) },
                onClick = {
                    onExpendedChange(false)
                    onSecondItemClick()
                },
                leadingIcon = { Icon(secondItemIcon, contentDescription = null) }
            )
        }
    }

}