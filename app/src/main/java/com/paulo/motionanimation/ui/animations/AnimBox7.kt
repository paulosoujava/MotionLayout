package com.paulo.motionanimation.ui.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.paulo.motionanimation.R
import com.paulo.motionanimation.ui.text.AnimatedText


@Preview
@OptIn(ExperimentalMotionApi::class)
@Composable
fun Five() {
    val ctx = LocalContext.current
    val density = LocalDensity.current

    val motionScene = remember {
        ctx.resources
            .openRawResource(R.raw.box13)
            .readBytes()
            .decodeToString()
    }
    var animateEnd by remember { mutableStateOf(true) }
    val progress by animateFloatAsState(
        targetValue = if (animateEnd) 1f else 0f,
        animationSpec = tween(1000), label = ""
    )
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
        ) {

            Box(
                modifier = Modifier
                    .layoutId("box_demo")
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .background(Color.Black)
                    .clickable {
                        animateEnd = !animateEnd
                    },
                contentAlignment = Alignment.TopStart
            ) {
                androidx.compose.animation.AnimatedVisibility(
                    visible = !animateEnd,
                    enter = fadeIn(animationSpec = tween(1000)),
                    exit = fadeOut(animationSpec = tween(1000))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_more_vert_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                        AnimatedText(
                            text = "BINANCE",
                            textColor = Color.White,
                            useAnimation = true,
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = animateEnd,
                    enter = fadeIn(animationSpec = tween(1000)),
                    exit = fadeOut(animationSpec = tween(1000))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .graphicsLayer(
                                    scaleX= progress
                                )
                                .padding(bottom = 10.dp)
                                .fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_share_24),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(
                                text = "BINANCE",
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 10.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .graphicsLayer(
                                    scaleY = progress,
                                )
                                .padding(bottom = 20.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Bitcoin",
                                color = Color.White,
                            )
                            Text(
                                text = "Ethereum",
                                color = Color.White,
                            )
                            Text(
                                text = "Solona",
                                color = Color(0xFFFFC107),
                                style = TextStyle(
                                    textDecoration = TextDecoration.combine(
                                        decorations = listOf(
                                            TextDecoration.Underline
                                        )
                                    ),
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Theter",
                                color = Color.White,
                            )
                        }
                        Row(
                            modifier = Modifier
                                .graphicsLayer(
                                    scaleY = progress,
                                )
                                .padding(top = 20.dp)
                                .fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(
                                text = "Solona",
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 1.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .graphicsLayer(
                                    scaleY = progress,
                                )
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, start = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text = "MX$ 606.43",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "+1.69%",
                                fontSize = 14.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .graphicsLayer(
                                        scaleY = progress,
                                        alpha = progress,
                                        rotationZ = progress
                                    )
                                    .clip(RoundedCornerShape(50))
                                    .background(
                                        Color(0xFF5BB950),
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(9.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .graphicsLayer(
                                    scaleY = progress,
                                    clip = true
                                )
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Buy",
                                fontSize = 17.sp,
                                color = Color.White,

                                modifier = Modifier
                                    .size(120.dp, 40.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(
                                        Color(0xFF5BB950),
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(9.dp),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(30.dp))

                            Text(
                                text = "Buy",
                                fontSize = 17.sp,
                                color = Color.White,

                                modifier = Modifier
                                    .size(120.dp, 40.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(
                                        Color(0xFFE91E63),
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(9.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

    }
}