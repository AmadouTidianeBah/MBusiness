package com.tbmob.m_business.presentation.admin.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tbmob.m_business.core.dateFormater
import com.tbmob.m_business.data.local.entities.relations.SalesWithProductAndUser
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun AdminProductItem(
    modifier: Modifier = Modifier,
    soldProduct: SalesWithProductAndUser,
) {
    Card(
        modifier = modifier
            .heightIn(min = 76.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            if (soldProduct.product.image == null) {
                Image(
                    imageVector = Icons.Outlined.Image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(74.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(soldProduct.product.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(74.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = soldProduct.product.name,
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                    overflow = TextOverflow.Clip
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sold: ${soldProduct.sales.quantity}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.Red.copy(alpha = .5f)
                    )
                    Text(
                        text = dateFormater(soldProduct.sales.dateSold),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = soldProduct.user.fullName,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminHomePre() {
    MBusinessTheme {
//        AdminProductItem()
    }
}