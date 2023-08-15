package com.paulo.motionanimation.ui.shape

import androidx.compose.ui.graphics.Shape
import android.util.Size
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.ceil


class LeftWavyShape(
    private val cornerRadius: Dp,
    private val period: Dp,
    private val amplitude: Dp
) : Shape {
    override fun createOutline(size: androidx.compose.ui.geometry.Size, layoutDirection: LayoutDirection, density: Density): Outline = Outline.Generic(Path().apply {
        val radius = with(density) { cornerRadius.toPx() }

        val wavyPath = Path().apply {
            val halfPeriod = with(density) { period.toPx() } / 2
            val amplitude = with(density) { amplitude.toPx() }

            moveTo(x = 0f, y = 0f)
            lineTo(0f, radius)
            repeat((ceil((size.height - radius) / halfPeriod + 1).toInt()) - 4) { i ->
                relativeQuadraticBezierTo(
                    dx1 = amplitude * (if (i % 2 == 0) -1 else 1),
                    dy1 = halfPeriod / 2,
                    dx2 = 0f,
                    dy2 = halfPeriod
                )
            }
            lineTo(0f, size.height - radius)
            lineTo(0f, size.height)
            lineTo(size.width, size.height)
            lineTo(size.width, 0f)
        }
        val boundsPath = Path().apply {
            addRoundRect(
                RoundRect(
                    cornerRadius = CornerRadius(radius),
                    rect = Rect(offset = Offset.Zero, size = size)
                )
            )

        }
        op(wavyPath, boundsPath, PathOperation.Intersect)
    })
}

class BottomWavyShape(
    private val cornerRadius: Dp,
    private val period: Dp,
    private val amplitude: Dp,
) : Shape {
    override fun createOutline(size: androidx.compose.ui.geometry.Size, layoutDirection: LayoutDirection, density: Density): Outline = Outline.Generic(Path().apply {

        val radius = with(density) { cornerRadius.toPx() }

        val wavyPath = Path().apply {
            val halfPeriod = with(density) { period.toPx() } / 2
            val amplitude = with(density) { amplitude.toPx() }
            moveTo(x = 0f, y = 0f)
            lineTo(x = 0f, size.height)
            lineTo(radius, size.height)
            repeat(ceil(size.width / halfPeriod + 1).toInt() - 4) { i ->
                relativeQuadraticBezierTo(
                    dx1 = halfPeriod / 2,
                    dy1 = 2 * amplitude * (if (i % 2 == 0) 1 else -1),
                    dx2 = halfPeriod,
                    dy2 = 0f,
                )
            }
            lineTo(size.width - radius, size.height)
            lineTo(size.width, size.height)
            lineTo(size.width, 0f)
        }
        val boundsPath = Path().apply {
            addRoundRect(
                RoundRect(
                    cornerRadius = CornerRadius(radius),
                    rect = Rect(offset = Offset.Zero, size = size)
                )
            )

        }
        op(wavyPath, boundsPath, PathOperation.Intersect)
    })
}

@Composable
fun ReceiptCardBottomWavy(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 12.dp)
            .fillMaxWidth(),
        shape = BottomWavyShape(cornerRadius = 10.dp, period = 30.dp, amplitude = 8.dp),
        border = BorderStroke(1.dp, color = Color.Black),
    ) {
        content()
    }
}

@Composable
fun ReceiptCardLeftWavy(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 12.dp)
            .fillMaxWidth(),
        shape = LeftWavyShape(cornerRadius = 10.dp, period = 12.dp, amplitude = 8.dp),
        border = BorderStroke(1.dp, color = Color.Red),

    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun ReceiptCard_Preview() {
    Column {
        Box(modifier = Modifier.padding(20.dp)) {
            ReceiptCardBottomWavy {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
        Box(modifier = Modifier.padding(20.dp)) {
            ReceiptCardLeftWavy {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }

}