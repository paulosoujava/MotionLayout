package com.paulo.motionanimation.ui.animations

import android.os.Build
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Divider


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.paulo.motionanimation.R
import kotlin.math.absoluteValue
import kotlin.math.min


@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CubePager() {
    val state = rememberPagerState()

    val scale by remember {
        derivedStateOf {
            1f - (state.currentPageOffset.absoluteValue) * .3f
        }
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val offsetFromStart = state.currentPageOffset
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .offset { IntOffset(0, 150.dp.roundToPx()) }
                .scale(scaleX = .6f, scaleY = .24f)
                .scale(scale)
                .rotate(offsetFromStart * 90f)
                .blur(
                    radius = 110.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded,
                )
                .background(Color.Black.copy(alpha = .5f))
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
            Column {
                repeat(4) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Color.DarkGray)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Row {
                repeat(4) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context).data(data = R.drawable.gears).apply(block = {
                                size(Size.ORIGINAL)
                            }).build(), imageLoader = imageLoader
                        ),
                        contentDescription = null,
                    )
                }

            }


            HorizontalPager(
                count = 9,
                state = state,
                modifier = Modifier
                    .scale(scaleX = 1f, scaleY = scale)
                    .aspectRatio(1f),
            ) { page ->


                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .graphicsLayer {
                            val pageOffset = state.currentPageOffset
                            val offScreenRight = pageOffset < 0f
                            val deg = 105f
                            val interpolated = FastOutLinearInEasing.transform(pageOffset.absoluteValue)
                            rotationY = min(interpolated * if (offScreenRight) deg else -deg, 90f)

                            transformOrigin = TransformOrigin(
                                pivotFractionX = if (offScreenRight) 0f else 1f,
                                pivotFractionY = .5f
                            )
                        }
                        .drawWithContent {
                            val pageOffset = state.currentPageOffset

                            this.drawContent()
                            drawRect(
                                Color.Black.copy(
                                    (pageOffset.absoluteValue * .7f)
                                )
                            )
                        }
                        .background(Color.LightGray.copy(alpha = .8f)),
                    contentAlignment = Alignment.Center
                ) {


                    AsyncImage(
                        model = "https://source.unsplash.com/random?desert,dune,$page",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = "Hello", style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = .6f),
                                blurRadius = 30f,
                            )
                        )
                    )
                }

            }
        }
    }
}