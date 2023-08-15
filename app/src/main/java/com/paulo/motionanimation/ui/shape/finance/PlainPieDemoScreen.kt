package com.paulo.motionanimation.ui.shape.finance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulo.motionanimation.ui.theme.*
import com.paulo.motionanimation.ui.shape.finance.composables.PlainPie

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
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
                keyColor = DarkTeal,
                currentValue = 15.0f,
                fullValue = 50.0f
            )

            PlainPie(
                keyColor = DarkTeal,
                currentValue = 23.0f,
                fullValue = 90.0f
            )
        }

        PlainPie(
            keyColor = DarkTeal,
            currentValue = 2.0f,
            fullValue = 13.0f,
            pieSize = 150.dp,
            strokeSize = 40.0f
        )

        PlainPie(
            keyColor = Purple40,
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