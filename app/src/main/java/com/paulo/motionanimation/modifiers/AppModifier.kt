package com.paulo.motionanimation.modifiers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun MyBox2WrongWay(
    modifier: Modifier = Modifier,
    isFocused: Boolean = false
) {
    val background = if (isFocused) Color.Black else Color.Red
    val border = if (isFocused) BorderStroke(1.dp, Color.Black)
    else BorderStroke(2.dp, Color.Red)
    Box(
        modifier = modifier
            .border(border)
            .background(background)
    ) {
        Text("MODIFIER CUSTOM WRONG WAY")
    }
}


fun Modifier.thenIf(condition: Boolean, modifier: Modifier.() -> Modifier) =
    if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }

fun Modifier.thenIf2(
    condition: Boolean,
    vararg modifier: Modifier.() -> Modifier
): Modifier =
    if (condition) {
        var all: Modifier = Modifier
        modifier.forEach {
            all = all.then(it(Modifier))
        }
        then(all)
    } else {
        this
    }

@Preview
@Composable
fun show() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyBox(isFocused = true)
        MyBox(isFocused = false)
        Spacer(modifier = Modifier.size(20.dp))
        MyBox2(isFocused = true)
        MyBox2(isFocused = false)
    }


}

@Composable
fun MyBox2(
    modifier: Modifier = Modifier,
    isFocused: Boolean
) {
    Box(
        modifier = modifier
            .thenIf2(
                condition = isFocused,
                { border(border = BorderStroke(1.dp, Color.Black)) },
                { background(Color.LightGray) }
            )
    ) {
        Text("MODIFIER CUSTOM 2")
    }
}

@Composable
fun MyBox(
    modifier: Modifier = Modifier,
    isFocused: Boolean
) {
    Box(
        modifier = modifier
            .thenIf(isFocused) {
                border(border = BorderStroke(1.dp, Color.Black))
            }
            .thenIf(isFocused) {
                background(Color.LightGray)
            }
    ) {
        Text("MODIFIER CUSTOM 1")
    }
}