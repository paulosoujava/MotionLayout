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
fun ConstraintLayoutSimple2() {
    ConstraintLayout(
        ConstraintSet {
          val button = createRefFor("button")
          val title = createRefFor("title")
          val g1 = createGuidelineFromStart(88.dp)

            constrain(button){
                top.linkTo(title.bottom, 32.dp)
                start.linkTo(g1)
            }
            constrain(title){
               centerVerticallyTo(parent)
                start.linkTo(g1)
            }
        },

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