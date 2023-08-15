package com.paulo.motionanimation.ui.animations



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.paulo.motionanimation.R
import com.paulo.motionanimation.ui.text.AnimatedText

/**
 * A demo of using MotionLayout as a collapsing Toolbar using JSON to define the MotionScene
 */
@OptIn(ExperimentalMotionApi::class)
@Preview(group = "scroll", device = "spec:shape=Normal,width=480,height=800,unit=dp,dpi=440")
@Composable
fun Notification() {

    val anime = remember {
        mutableStateOf(true)
    }
    val animateSize = animateIntAsState(
        targetValue = if (anime.value) 88 else 210,
        animationSpec = tween(1000), label = ""
    )

    val enabled = remember {
        mutableStateOf(true)
    }
    val borderRadius by animateIntAsState(
        targetValue = if (anime.value) 100 else 0,
        animationSpec = tween(
            durationMillis = 1000
        ), label = ""
    )
    var expanded by remember { mutableStateOf(false) }
    val width: Dp by animateDpAsState(
        targetValue = if (expanded) 300.dp else 100.dp,
        animationSpec = tween(durationMillis = 500), label = ""
    )
    val color: Color by animateColorAsState(
        targetValue = if (expanded) Color.Red else Color.Transparent,
        animationSpec = tween(durationMillis = 500), label = ""
    )
    Box(
        modifier = Modifier
            .clickable {
                anime.value = !anime.value
            }
            .padding(top=10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .size(400.dp, animateSize.value.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)
                .padding(12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(text = "youtube music", color = Color(0xFF806A28).copy(alpha = .6f), fontSize = 13.sp)
                    AnimatedText(
                        text = "Name of Music", textColor = Color(0xFF806A28),
                        useAnimation = true
                    )
                    Text(text = "Name of band", color = Color(0xFF665319), fontSize = 13.sp)
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile), contentDescription = null,
                        modifier = Modifier
                            .graphicsLayer(
                                alpha = 0.5f,
                                shadowElevation = 10f
                            )
                            .clip(RoundedCornerShape(borderRadius)),

                        )
                    //VideoPlayingAnimation()
                }

            }

            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.ThumbUp,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.baseline_skip_previous_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.baseline_pause_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.baseline_skip_next_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            Icons.TwoTone.Favorite,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .background(color)
                            .width(width)
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.animation.AnimatedVisibility(visible = expanded) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "SIM", color = Color.White, modifier = Modifier
                                    .background(Color.Black, shape = RoundedCornerShape(10))
                                    .clip(RoundedCornerShape(10))
                                    .padding(4.dp)
                                    .clickable {
                                        expanded = !expanded
                                    })
                                Text(text = "Remove dos favoritos?", color = Color.White)
                                Text(text = "NÃO", color = Color.White, modifier = Modifier
                                    .background(Color.Black, shape = RoundedCornerShape(10))
                                    .clip(RoundedCornerShape(10))
                                    .padding(4.dp)
                                    .clickable {
                                        expanded = !expanded
                                    })
                            }

                        }

                    }
                }

            }

        }
    }

}



fun Float.interpolateColor(startColor: Color, endColor: Color): Color {
    return lerp(startColor, endColor, this)
}

fun Float.interpolateValue(startValue: Dp, endValue: Dp): Dp {
    return lerp(startValue, endValue, this)
}