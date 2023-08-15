package com.paulo.motionanimation.ui.cards

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
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

@Preview
@Composable
fun ShowCardActions() {

    val context = LocalContext.current

    LazyColumn{
        items(10){
            Box(modifier = Modifier.padding(10.dp)){
                motionExample1(context)
            }

        }
    }

}

@OptIn(ExperimentalMotionApi::class)
@Composable
private fun motionExample1(context: Context) {
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.box8)
            .readBytes()
            .decodeToString()
    }
    var animateEnd by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (animateEnd) 1f else 0f,
        animationSpec = tween(1000)
    )

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val properties = motionProperties(id = "box_demo")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color.Red, shape = RoundedCornerShape(30.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = Color.Gray,
                            shape = CircleShape
                        )
                )
                Text(
                    text = "Paulo Oliveira",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .layoutId("box_demo")
                .background(Color.Red, shape = RoundedCornerShape(30.dp)),
            contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                val isSHow = (properties.value.int("show") > 1)
                val iconId = if (isSHow) R.drawable.profile else R.drawable.baseline_more_vert_24
                AnimatedVisibility(visible = !isSHow) {
                    IconButton(onClick = { animateEnd = !animateEnd }) {
                        Icon(
                            painter = painterResource(id = iconId),
                            contentDescription = null,
                            tint = if (!isSHow) Color.Black else Color.Transparent,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                SHowIcon(isSHow = isSHow, icon = R.drawable.baseline_close_24) {
                    animateEnd = !animateEnd
                }
                SHowIcon(isSHow = isSHow, icon = R.drawable.baseline_favorite_border_24) {
                    Toast.makeText(context, "FAVORITE", Toast.LENGTH_SHORT).show()
                }
                SHowIcon(isSHow = isSHow, icon = R.drawable.baseline_share_24) {
                    Toast.makeText(context, "SHARE", Toast.LENGTH_SHORT).show()
                }
                SHowIcon(isSHow = isSHow, icon = R.drawable.baseline_report_gmailerrorred_24) {
                    Toast.makeText(context, "REPORT", Toast.LENGTH_SHORT).show()
                }


            }

        }
    }
}

@Composable
fun SHowIcon(isSHow: Boolean, icon: Int, onClick: () -> Unit) {
    AnimatedVisibility(visible = isSHow) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun ShowCardAction(){
    ShowCardActions()
}