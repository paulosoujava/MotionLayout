package com.paulo.motionanimation.ui.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Ticket(
    animated:Boolean,
    content: @Composable BoxScope.() -> Unit
) {
    val size = if(animated) 450 else 150
    Box(
        modifier = Modifier
            .padding(20.dp)
            .clip(
                RoundedCornerShape(5)
            )
            .fillMaxWidth()
            .height(size.dp)
            .background(Color.Black.copy(alpha = .9f)),
        contentAlignment = Alignment.Center,
    ) {

        // Top black half circle.
        CardBlackHalfCircles(modifier = Modifier.align(alignment = Alignment.TopCenter))

        content()

        CardBlackHalfCircles(modifier = Modifier.align(alignment = Alignment.BottomCenter))
    }
}

@Composable
private fun CardBlackHalfCircles(
    modifier: Modifier
) {
    Canvas(
        modifier = modifier
            .border(color = Color.Magenta, width = 2.dp)
    ) {
        drawCircle(
            color = Color.White,
            radius = 24.dp.toPx()
        )
    }
}