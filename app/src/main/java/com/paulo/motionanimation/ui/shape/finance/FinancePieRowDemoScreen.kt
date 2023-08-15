package com.paulo.motionanimation.ui.shape.finance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.paulo.motionanimation.R
import com.paulo.motionanimation.ui.theme.*

import com.paulo.motionanimation.ui.shape.finance.composables.FinancePieData
import com.paulo.motionanimation.ui.shape.finance.composables.FinancePieRowView

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FinancePieRowDemoScreen() {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        FinancePieRowView(
            pieDataPair = Pair(
                FinancePieData("Today", 181.39f, 1000f, Pink40),
                FinancePieData("March", 734.02f, 1000f, Color.Blue)
            )
        )

        FinancePieRowView(
            pieDataPair = Pair(
                FinancePieData("Entertainment", 5.01f, 300f, LightCarmin),
                FinancePieData("Restaurant", 120.02f, 500f, DarkTeal)
            )
        )

        FinancePieRowView(
            pieDataPair = Pair(
                FinancePieData("Services", 51.01f, 300f, LightCarmin),
                FinancePieData("Transport", 220.02f, 500f, DarkTeal)
            )
        )
    }
}