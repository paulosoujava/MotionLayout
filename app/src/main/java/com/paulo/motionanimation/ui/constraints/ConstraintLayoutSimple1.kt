package com.paulo.motionanimation.ui.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview
@Composable
fun ConstraintLayoutSimple1() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (button, title) = createRefs()
        val guideline1 = createGuidelineFromStart(36.dp)

        Text(
            text = "My Title",
            fontSize = 24.sp,
            modifier = Modifier
                .constrainAs(title) {
                    centerVerticallyTo(parent)
                    start.linkTo(guideline1)
                })
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(button) {
                   top.linkTo(title.bottom, margin = 16.dp)
                    start.linkTo(guideline1)
                }
        ) {
            Text("Button")
        }
    }
}