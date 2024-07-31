package com.tbmob.m_business.presentation.admin.user.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.User
import com.tbmob.m_business.presentation.common.RowInfo

@Composable
fun HomeUserItem(
    modifier: Modifier = Modifier,
    user: User,
    onUserClicked: (User) -> Unit,
    onDeleteUserClicked: (User) -> Unit
) {
    Card(
        modifier = modifier
            .padding(12.dp)
            .clickable { onUserClicked(user) }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            RowInfo(title = "Name:", value = user.fullName)
            Spacer(modifier = Modifier.height(8.dp))
            RowInfo(title = "Username:", value = user.username)
            Spacer(modifier = Modifier.height(8.dp))
            RowInfo(title = "Post:", value = if (user.admin) "Admin" else "Employee")
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = Color.Red.copy(alpha = .7f),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onDeleteUserClicked(user) }
                )
            }
        }
    }
}