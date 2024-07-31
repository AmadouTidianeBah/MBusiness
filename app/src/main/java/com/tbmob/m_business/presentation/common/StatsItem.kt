package com.tbmob.m_business.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tbmob.m_business.data.local.entities.Product
import com.tbmob.m_business.presentation.ui.theme.MBusinessTheme

@Composable
fun StatsItem(
    modifier: Modifier = Modifier,
    product: Product,
    soldIndicator: Int,
    canvasSize: Dp = 100.dp,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = .2f),
    backgroundIndicatorWith: Float = 20f,
    foregroundIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    foregroundIndicatorWith: Float = 20f
) {
    var animateIndicatorValue by remember {
        mutableFloatStateOf(0f)
    }
    var allowedIndicatorValue by remember {
        mutableIntStateOf(product.quantity)
    }

    allowedIndicatorValue = if (soldIndicator <= product.quantity) soldIndicator
                                else product.quantity

    LaunchedEffect(key1 = allowedIndicatorValue) {
        animateIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage = (animateIndicatorValue / product.quantity) * 100
    val sweepAngle by animateFloatAsState(
        targetValue = (3.6 * percentage).toFloat(),
        animationSpec = tween(1000),
        label = "sweep angle animation"
    )

    Column(
        modifier = modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWith = backgroundIndicatorWith
                )

                foregroundIndicator(
                    componentSize = componentSize,
                    sweepAngle = sweepAngle,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWith = foregroundIndicatorWith
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductImage(productImage = product.image, modifier = Modifier.size(52.dp))
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWith: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = -90f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWith,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    componentSize: Size,
    sweepAngle: Float,
    indicatorColor: Color,
    indicatorStrokeWith: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = -90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWith,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StatsItemPre() {
    MBusinessTheme {
//        StatsItem()
    }
}