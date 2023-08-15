package com.paulo.motionanimation.ui.shape


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.paulo.motionanimation.R














@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun PlainPieDemoScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PlainPie(
                keyColor = Color.Blue,
                currentValue = 5.0f,
                fullValue = 10.0f
            )

            PlainPie(
                keyColor = Color.Cyan,
                currentValue = 15.0f,
                fullValue = 50.0f
            )

            PlainPie(
                keyColor = Color.Red,
                currentValue = 23.0f,
                fullValue = 90.0f
            )
        }

        PlainPie(
            keyColor = Color.Red,
            currentValue = 2.0f,
            fullValue = 13.0f,
            pieSize = 150.dp,
            strokeSize = 40.0f
        )

        PlainPie(
            keyColor = Color.Blue,
            currentValue = 9.0f,
            fullValue = 13.0f,
            pieSize = 200.dp,
            strokeSize = 50.0f
        )

        PlainPie(
            keyColor = Color.Black,
            currentValue = 10.0f,
            fullValue = 13.0f,
            pieSize = 350.dp,
            strokeSize = 90.0f
        )
    }
}

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun PlainPie(
    currentValue: Float = 181.39f,
    fullValue: Float = 1000f,
    keyColor: Color = Color.DarkGray,
    pieSize: Dp = 70.dp,
    strokeSize: Float = 20.0f
) {
    val fullColor = keyColor.copy(alpha = 0.3f)

    val currentAngle = currentValue / fullValue * 360f

    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(currentAngle) {
        animatedProgress.animateTo(
            currentAngle,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessHigh
            )
        )
    }

    Canvas(
        modifier = Modifier
            .size(pieSize)
            .padding(16.dp)
    ) {
        val canvasSize = size.minDimension
        val radius = canvasSize / 2
        val center = Offset(canvasSize / 2, canvasSize / 2)

        drawCircle(
            color = fullColor,
            radius = radius,
            center = center
        )

        drawArc(
            color = keyColor,
            startAngle = 250f,
            sweepAngle = animatedProgress.value,
            useCenter = true
        )

        drawCircle(
            color = Color.White,
            radius = radius * 0.8f,
            center = center
        )
    }
}