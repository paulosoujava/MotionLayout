package com.paulo.motionanimation.ui.shape.finance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.az.financeapp.ui.screens.AssetCardListDemoScreen
import com.paulo.motionanimation.R

import com.paulo.motionanimation.ui.shape.finance.composables.CryptoCardStyle
import com.paulo.motionanimation.ui.shape.finance.composables.CryptoCardData
import com.paulo.motionanimation.ui.shape.finance.composables.CryptoCard

@Composable
@Preview(showBackground = true)
fun CryptoWalletHomeScreen() {
    Column {
        CryptoWalletCoinCardPairRow()

        AssetCardListDemoScreen()
    }
}

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CryptoWalletCoinCardPairRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        CryptoCard(
            style = CryptoCardStyle.Dark,
            data = CryptoCardData(
                name = "Bitcoin",
                icon = R.drawable.ic_btc,
                value = 3.689087f,
                valueChange = -18,
                currentTotal = 98160
            )
        )

        CryptoCard(
            style = CryptoCardStyle.Light,
            data = CryptoCardData(
                name = "Ethereum",
                icon = R.drawable.ic_ethereum,
                value = 94.48096f,
                valueChange = 26,
                currentTotal = 180480
            )
        )
    }

}