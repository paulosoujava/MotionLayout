package com.paulo.motionanimation.ui.shape.finance.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.paulo.motionanimation.R
import com.paulo.motionanimation.ui.theme.*


data class FinancePieData(
    val label: String,
    val currentValue: Float,
    val fullValue: Float,
    val keyColor: Color
)

private val mockTodayValue = FinancePieData("Today", 181.39f, 1000f, DarkTeal)
private val mockMarchValue = FinancePieData("March", 734.02f, 1000f, DarkPurple)

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FinancePieRowView(
    pieDataPair: Pair<FinancePieData, FinancePieData> = Pair(
        mockTodayValue,
        mockMarchValue
    )
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        FinancePieView(pieDataPair.first)

        Divider(
            Modifier
                .width(2.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(4.dp))
                .align(CenterVertically), color = Color(0xffe8eaed)
        )

        FinancePieView(pieDataPair.second)
    }
}

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun FinancePieView(
    pieData: FinancePieData = FinancePieData("Today", 181.39f, 1000f, DarkPurple)
) {
    Row(verticalAlignment = CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        PlainPie(pieData.currentValue, pieData.fullValue, pieData.keyColor)

        SummaryView(pieData.label, pieData.currentValue)
    }
}

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun SummaryView(label: String = "Today", currentValue: Float = 181.39f) {
    Column(modifier = Modifier.padding(4.dp), verticalArrangement = Arrangement.Bottom) {
        Text(text = label, style = MaterialTheme.typography.titleSmall, color = Color.Gray)
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "$${currentValue.toInt()}",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = getFloatingPoint(currentValue),
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}

private fun getFloatingPoint(floatNumber: Float): String {
    val stringNumber = floatNumber.toString()
    val index = stringNumber.indexOf('.')
    return stringNumber.substring(index)
}

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun PlainPie(
    currentValue: Float = 181.39f,
    fullValue: Float = 1000f,
    keyColor: Color = DarkTeal,
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