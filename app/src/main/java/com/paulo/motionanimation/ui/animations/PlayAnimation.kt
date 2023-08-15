package com.paulo.motionanimation.ui.animations

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.paulo.motionanimation.R

@Preview
@Composable
fun VideoPlayingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    val height1 by infiniteTransition.animateFloat(
        initialValue = 13f,
        targetValue = 13f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 500
                0.7f at 200
            },
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val height2 by infiniteTransition.animateFloat(
        initialValue = 10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 450
                0.7f at 200
            },
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )


    val height3 by infiniteTransition.animateFloat(
        initialValue = 8f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 400
                0.7f at 200
            },
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(height1.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        Color.White
                    )
            )
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(height2.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        Color.White
                    )
            )
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(height3.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        Color.White
                    )
            )
        }
    }
}


@Composable
fun ChannelShortPlaying() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.size(60.dp)) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier.clip(
                    RoundedCornerShape(8.dp)
                ),
                contentScale = ContentScale.Crop
            )
        }

        Box(modifier = Modifier
            .zIndex(2f)
            .graphicsLayer {
                translationX = 50f
                translationY = 50f
            }) {
            Row(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)) {
                VideoPlayingAnimation()
            }
        }
    }
}