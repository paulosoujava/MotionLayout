package com.paulo.motionanimation.ui.animations

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.paulo.motionanimation.R
import com.paulo.motionanimation.ui.util.getJson

@Preview
@OptIn(ExperimentalMotionApi::class)
@Composable
fun AnimBox3() {
    var animateToEnd by remember { mutableStateOf(true) }
    val progress by animateFloatAsState(
        targetValue = if (animateToEnd) 1f else 0f,
        animationSpec = tween(3000), label = ""
    )
    val context = LocalContext.current
    val motionScene = remember {
        getJson(context, R.raw.box3)
    }

    MotionLayout(
        motionScene = MotionScene(motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .layoutId("box_demo")
                .background(Color.Red)
                .clickable {
                    animateToEnd = !animateToEnd

                }
        ) {

        }
    }

}