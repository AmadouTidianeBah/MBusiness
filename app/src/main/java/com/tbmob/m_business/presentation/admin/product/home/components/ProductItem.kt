package com.tbmob.m_business.presentation.admin.product.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tbmob.m_business.core.formatNumber
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.presentation.common.RowInfo

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onProductClicked: (Product) -> Unit,
    onDeleteProductClicked: (Product) -> Unit
) {
    Card(
        modifier = modifier
            .heightIn(min = 76.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable { onProductClicked(product) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            if (product.image == null) {
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
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(74.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        overflow = TextOverflow.Clip
                    )
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null,
                        tint = Color.Red.copy(alpha = .7f),
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { onDeleteProductClicked(product) }
                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
                RowInfo(title = "Quantity:", value = product.quantity.toString())
                Spacer(modifier = Modifier.height(8.dp))
                RowInfo(title = "Base-Price:", value = formatNumber(product.basePrice) + " Gnf")
                Spacer(modifier = Modifier.height(8.dp))
                RowInfo(title = "Sell-Price:", value = formatNumber(product.sellPrice) + " Gnf")
            }
        }
    }
}