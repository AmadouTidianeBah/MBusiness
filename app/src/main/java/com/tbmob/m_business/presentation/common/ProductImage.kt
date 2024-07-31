package com.tbmob.m_business.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductImage(
    modifier: Modifier = Modifier,
    productImage: ByteArray?
) {
    if (productImage == null) {
        Image(
            imageVector = Icons.Outlined.Image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(74.dp)
                .clip(CircleShape)
        )
    } else {
        Image(
            painter = rememberAsyncImagePainter(productImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(74.dp)
                .clip(CircleShape)
        )
    }
}