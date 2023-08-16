package com.paulo.motionanimation.ui.previews

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showSystemUi = true,  device = Devices.PHONE, name = "PHONE")
@Preview(showSystemUi = true,  device = Devices.NEXUS_7_2013, name = "NEXUS_7_2013")
@Preview(showSystemUi = true,  device = Devices.NEXUS_5, name = "NEXUS_5")
@Preview(
    showSystemUi = true,
    device = Devices.FOLDABLE, name = "FOLDABLE",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 200,
    heightDp = 200,
    fontScale = 5f,
    locale = "BR",
    backgroundColor = 0x00000000
)
annotation class DevicesPreview



@DevicesPreview
@Composable
fun NBox1() {
    Box(
        modifier = Modifier
            .size(400.dp)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ){
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "MEU TESTE")
        }
    }
}