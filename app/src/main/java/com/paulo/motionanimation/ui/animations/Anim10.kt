package com.paulo.motionanimation.ui.animations

import android.content.Context
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.paulo.motionanimation.R
import com.paulo.motionanimation.ui.text.AnimatedText


@Preview
@Composable
fun ShowBoxAnimate() {

    var animateEnd by remember { mutableStateOf(true) }
    val progress by animateFloatAsState(
        targetValue = if (animateEnd) 1f else 0f,
        animationSpec = tween(1000), label = ""
    )
    val alpha: Float by animateFloatAsState(if (animateEnd) 1f else 0.5f, label = "")


    Box {
        GifImage(modifier = Modifier
            .fillMaxWidth()
            .height(340.dp))

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,

            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(
                    alpha = alpha,
                    clip = true,
                    spotShadowColor = Color.Red.copy(alpha = 0.5f),
                   // translationY = progress * 100f,
                    ambientShadowColor = Color.Red.copy(alpha = 0.5f),

                    )
                .clickable {
                    animateEnd = !animateEnd
                }
        )
    }

}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.code).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxWidth(),
    )
}