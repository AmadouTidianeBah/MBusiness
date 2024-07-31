package com.tbmob.m_business.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenIntro(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = subTitle,
            fontSize = 16.sp,
        )
    }
}