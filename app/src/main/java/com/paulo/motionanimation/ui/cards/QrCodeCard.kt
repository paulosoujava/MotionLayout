package com.paulo.motionanimation.ui.cards

import androidx.compose.animation.AnimatedVisibility
import com.paulo.motionanimation.R


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



data class User(
    val username: String = "Paulo Oliveira",
    val instagram: String = "paulosoujava",
    val userId: String = "09124501293845",
    val date: String = "QUA AGO 14",
    val time: String = "03:24 P.M.",
    val userImage: Int = R.drawable.profile,
    val userQrCode: Int = R.drawable.baseline_qr_code_24,
)

private val spaceBetweenItems = 28.dp
private val framePadding = 24.dp


@Composable
fun QrCodeTicket(
    user: User,

) {
    val animated = remember {
        mutableStateOf(false)
    }
    Ticket(animated = animated.value) {
        CardContent(
            date = user.date,
            time = user.time,
            instagram = user.instagram,
            userId = user.userId,
            username = user.username,
            userImage = user.userImage,
            userQrCode = user.userQrCode,
        ){
            animated.value = !animated.value
        }

    }
    
}



@Composable
fun CardContent(
    date: String,
    time: String,
    instagram: String,
    userId: String,
    username: String,
    userImage: Int,
    userQrCode: Int,
    onCLickAnimated: (Boolean) -> Unit
) {
    val animated = remember {
        mutableStateOf(false)
    }
    Column (
        modifier = Modifier.clickable {
            animated.value = !animated.value
            onCLickAnimated(animated.value)
        }
    ) {

        AnimatedVisibility(visible =  animated.value) {
           Column {
               Spacer(modifier = Modifier.height(spaceBetweenItems))

               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {

                   CardTitleText(title = "DATE", info = date)

                   CardBrand(modifier = Modifier.align(alignment = Alignment.Bottom))
               }

               Spacer(modifier = Modifier.height(spaceBetweenItems))

               CardTitleText(title = "TIME", info = time)

               Spacer(modifier = Modifier.height(spaceBetweenItems))

               CardTitleText(title = "FULL NAME", info = username)

               Spacer(modifier = Modifier.height(spaceBetweenItems))

               CardUserQrCode(userQrCode = userQrCode, modifier = Modifier.align(alignment = Alignment.Start))

               Spacer(modifier = Modifier.height(spaceBetweenItems))



               CardDashDivider()

               Spacer(modifier = Modifier.height(spaceBetweenItems))
           }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {

                CardUserImage(userImage = userImage)

                CardInstagram(text = instagram)
            }

            CardUserId(text = userId)
        }
    }
}

@Composable
fun CardTitleText(title: String, info: String) {
    Column {
        Text(
            modifier = Modifier.padding(
                horizontal = framePadding
            ),
            text = title,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
            )
        )

        Text(
            modifier = Modifier.padding(
                horizontal = framePadding
            ),
            text = info,
            color = Color.White,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace
            )
        )
    }
}

@Composable
fun CardBrand(
    modifier: Modifier
) {
    Image(
        modifier = modifier
            .padding(
                end = framePadding
            )
            .size(size = 42.dp),
        painter = painterResource(id = R.drawable.baseline_verified_24),
        contentDescription =""
    )
}

@Composable
fun CardDashDivider() {
    Canvas(
        Modifier
            .fillMaxWidth()
            .height(1.dp)) {
        drawLine(
            color = Color.White,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 14f), 0f)
        )
    }
}

@Composable
fun CardUserQrCode(userQrCode: Int, modifier: Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Image(
            modifier = modifier
                .padding(horizontal = framePadding)
                .size(size = 56.dp),
            painter = painterResource(id = userQrCode),
            colorFilter = ColorFilter.tint(
                color = Color.White
            ),
            contentDescription =""
        )
    }

}

@Composable
fun CardUserImage(userImage: Int) {
    Image(
        modifier = Modifier
            .padding(start = framePadding)
            .size(size = 42.dp)
            .clip(CircleShape) ,
        contentScale = ContentScale.Crop,
        painter = painterResource(id = userImage),
        contentDescription = ""
    )
}

@Composable
fun CardInstagram(text: String) {
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = text,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = FontFamily.Default,
            letterSpacing = 0.7.sp,
        )
    )
}

@Composable
fun CardUserId(text: String) {
    Text(
        modifier = Modifier.padding(end = framePadding),
        text = text,
        color = Color.White,
        fontWeight = FontWeight.Light,
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            letterSpacing = 1.sp,
        )
    )
}

@Composable
@Preview
fun CardFrontSidePreview() {

    QrCodeTicket(user = User())
}