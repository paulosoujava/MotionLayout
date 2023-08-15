package com.paulo.motionanimation.ui.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId

@Preview
@Composable
fun ConstraintLayoutSimple3() {
    ConstraintLayout(
        ConstraintSet(
          """ { 
              g1: { type: 'vGuideline', top: 26 },
              button: {
                top: ['title', 'bottom', 16],
                start: ['g1', 'start'],
                centerHorizontally: 'parent',
              },
              title: {
                centerHorizontally: 'parent',
                start: ['g1', 'start'],
              }
          }"""
        ),

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Text(
            text = "My Title",
            fontSize = 24.sp,
            modifier = Modifier.layoutId("title"))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.layoutId("button")
        ) {
            Text("Button")
        }
    }
}