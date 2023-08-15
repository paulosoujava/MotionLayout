package com.paulo.motionanimation.list

import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Preview
@Composable
fun TikTokList() {

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = configuration.screenWidthDp.dp * density
    val screenHeight = configuration.screenHeightDp.dp * density

    val displayMetrics = LocalDensity.current.density

    /*val screenWidth1 = with(LocalDensity.current) {resources ->
        (resources.displayMetrics.widthPixels / density).dp
    }
    val screenHeight1 = with(LocalDensity.current) {resources ->
        (resources.displayMetrics.heightPixels / density).dp
    }*/


val state = rememberLazyListState()
val scope = CoroutineScope(Dispatchers.Unconfined)
    var count = 0


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = state,
    ) {
        items(10) { index ->
            val isItemVisible = state.layoutInfo.visibleItemsInfo.any { it.key == index }
            println("${state.layoutInfo.visibleItemsInfo.size}")

            if(!state.isScrollInProgress && isItemVisible){
                LaunchedEffect(Unit){
                 scope.launch {
                     while (true){
                         println("Count: $count")
                         count++
                         delay(1000)
                         if(count == 5){
                             println("PODE TRAZER COISAS REFERENTE A ISTO")
                             count = 0
                             break
                         }
                     }
                 }
                }
            }

            Box(
                modifier = Modifier
                    .size(
                        width = screenWidth,
                        height = (screenHeight.value / density).dp
                    )
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Scroll isScrollInProgress: ${state.isScrollInProgress}", fontSize = 30.sp)
                    Text("Screen Width: $screenWidth", fontSize = 30.sp)
                    Text("Screen Height: ${(screenHeight.value / density).dp}", fontSize = 30.sp)
                }
            }
        }
    }
}

@Composable
fun TikTokListItem(videoUrl: String) {
    Text(text = "${videoUrl}", fontSize = 30.sp)


}
