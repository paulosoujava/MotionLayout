package com.paulo.motionanimation.swipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulo.motionanimation.organizeViews.HomeViewModel

@Preview
@Composable
fun ShowSwipe() {
    val homeViewModel = hiltViewModel<CardsScreenViewModel>()
    CardsScreen(homeViewModel)
}