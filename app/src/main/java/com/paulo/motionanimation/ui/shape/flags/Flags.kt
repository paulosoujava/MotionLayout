package com.paulo.motionanimation.ui.shape.flags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(device = Devices.NEXUS_6P, showSystemUi = true)
@Preview(device = Devices.NEXUS_6P, locale = "ar", showSystemUi = true)
@Composable
fun RTLScreen() {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
        SimpleButton(
            text = "RTL Friendly",
            shape = CutCornerShape(
                topStartPercent = 50,
                bottomStartPercent = 50
            ),
        )

        SimpleButton(
            text = "RTL Friendly",
            shape = RoundedCornerShape(
                topStartPercent = 50,
                bottomStartPercent = 50
            ),
        )

        SimpleButton(
            text = "Absolute",
            shape = AbsoluteCutCornerShape(
                topLeftPercent = 50,
                bottomLeftPercent = 50
            )
        )

        SimpleButton(
            text = "Absolute",
            shape = AbsoluteRoundedCornerShape(
                topLeftPercent = 50,
                bottomLeftPercent = 50
            )
        )
    }
}


@Composable
fun SimpleButton(text: String, shape: Shape) {
    Surface(
        shape = shape,
        color = Color.LightGray,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                start = 24.dp,
                end = 8.dp,
                top = 6.dp,
                bottom = 6.dp
            ),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun ContentTag(color: Color = Color.Black, tagName: String = "TAG") {
    Surface(
        shape = AbsoluteCutCornerShape(topLeftPercent = 50, bottomLeftPercent = 50),
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color)
                .padding(
                    start = MaterialTheme.typography.bodyLarge.fontSize.value.dp * 1.1f,
                    end = MaterialTheme.typography.bodyLarge.fontSize.value.dp / 2,
                    top = 4.dp,
                    bottom = 4.dp,
                )
        ) {
            Text(
                text = tagName,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.W300,
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.Center)
            )
        }
    }
}

val TearDropShape = RoundedCornerShape(
    topStartPercent = 50,
    topEndPercent = 50,
    bottomEndPercent = 10,
    bottomStartPercent = 50
)

@Preview
@Composable
fun TearDrop(modifier: Modifier = Modifier) {

    Surface(
        shape = TearDropShape,
        color = Color.Red,
        modifier = Modifier
            .padding(24.dp)
            .size(60.dp)
    ) {
        Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text(text = "7", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
        }
    }
}

val IncomingMessage = RoundedCornerShape(
    topStart = 8.dp,
    topEnd = 8.dp,
    bottomEnd = 8.dp,
    bottomStart = 0.dp
)

val OutgoingMessage = RoundedCornerShape(
    topStart = 8.dp,
    topEnd = 8.dp,
    bottomEnd = 0.dp,
    bottomStart = 8.dp
)

@Composable
fun MessageBubble(
    text: String,
    isIncoming: Boolean,
    modifier: Modifier = Modifier,
) {

    Surface(
        shape = if (isIncoming) IncomingMessage else OutgoingMessage,
        color = if (isIncoming) Color.Green else Color.Blue,
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Light, fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun ShowMessage() {
    Column {
        MessageBubble("You have an incoming message", true)
        MessageBubble("Cool!!", false, )
    }

}
@Preview
@ExperimentalMaterial3Api
@Composable
fun StadiumButton2(
    text: String = "teste",
    color:Color =  Color.Red
) {
    Surface(
        shape = RoundedCornerShape(percent = 50),
        color = color,
        modifier = Modifier.padding(12.dp),

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}

@Preview
@Composable
fun SupermanShape(size: Size = Size(50f,180f)) {
    Box(Modifier.rotate(45f)) {
        Surface(
            shape = AbsoluteCutCornerShape(topLeftPercent = 50),
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Red)

                    .height(size.height.dp)
                    .width(size.width.dp)
            ) {
            }
        }
    }
}

@Preview
@Composable
fun Kryptonite(color: Color = Color.Black, size: Size = Size(50f,180f)) {
    Surface(
        shape = AbsoluteCutCornerShape(percent = 50),
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color)
                .height(size.height.dp)
                .width(size.width.dp)
        ) {

        }
    }
}