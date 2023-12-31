package com.paulo.motionanimation.ui.animations

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.paulo.motionanimation.R


@OptIn(ExperimentalMotionApi::class)
@Preview
@Composable
fun DynamicIslandDemo2() {
    var expanded by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(1000), label = ""
    )

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.box11)
            .readBytes()
            .decodeToString()
    }

    var heightState by remember { mutableStateOf(48.dp) }
    var widthState by remember { mutableStateOf(196.dp) }
    var percentCornerState by remember {
        mutableStateOf(50)
    }
    var fontSizeState by remember {
        mutableStateOf(18.sp)
    }

    Box(
        modifier = Modifier
            .padding(16.dp), Alignment.TopCenter
    ) {
        MotionLayout(
            modifier = Modifier
                .width(widthState)
                .height(heightState)
                .clip(shape = RoundedCornerShape(percent = percentCornerState))
                .clickable { expanded = !expanded },
            motionScene = MotionScene(
                motionScene
            ),
            progress = progress
        ) {
            val newHeight = motionProperties(id = "surface").value.float("height")
            newHeight.let { heightState = it.dp }

            val newWidth = motionProperties(id = "surface").value.float("width")
            newWidth.let { widthState = it.dp }

            val percentCorner = motionProperties(id = "surface").value.int("corner")
            percentCorner.let { percentCornerState = it }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(percent = percentCorner))
                    .layoutId("surface")
                    .background(Color.Black)
            )
            val color = Color.fromHex("#EA0B8C")
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .layoutId("icCall"),
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "",
                tint = color
            )

            val fontSize = motionProperties(id = "durationLabel").value.fontSize("size")
            fontSize.let { fontSizeState = it }
            Text(text = "3 min", color = color, fontSize = fontSizeState, modifier = Modifier.layoutId("durationLabel"))
            Text(text = "Arrives in 3 min", color = Color.White,fontSize = 24.sp, modifier = Modifier.layoutId("nameLabel"))
            Text(text = "Paulo • 5.0 ⭐", color = Color.White, fontSize = 20.sp, modifier = Modifier.layoutId("lastNameLabel"))
            Text(text = "Ford Focus • 772GTY8G", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.layoutId("descriptionLabel"))


            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .layoutId("avatar")
                    .size(44.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(1.dp, Color.White, CircleShape)   // add a border (optional)
            )
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "avatar",
                contentScale = ContentScale.Inside,            // crop the image if it's not a square
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .layoutId("endCall")
                    .width(96.dp)
            )
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "avatar",
                contentScale = ContentScale.Inside,            // crop the image if it's not a square
                modifier = Modifier
                    .layoutId("startCall")
                    .width(104.dp)
            )

        }

    }


}

private fun Color.Companion.fromHex(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}