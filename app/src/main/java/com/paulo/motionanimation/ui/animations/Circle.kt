package com.paulo.motionanimation.ui.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId

public const val motionSceneStr = """
{
  ConstraintSets: {
    start: {
      circle: {
        width: 40,
        height: 40,
        start: ['parent', 'start', 0],
        top: ['parent', 'top', 0],
        bottom: ['parent', 'bottom', 0]
      },
    },
    
   middle: {
      circle: {
        width: 'spread',
        height: 40,
        start: ['parent', 'start', 0],
        end: ['parent', 'end', 0],
        top: ['parent', 'top', 0],
        bottom: ['parent', 'bottom', 0],
      },
    },
   
    end: {
      circle: {
        width: 40,
        height: 40,
        end: ['parent', 'end', 0],
        top: ['parent', 'top', 0],
        bottom: ['parent', 'bottom', 0],
      },
    },
  },
  Transitions: {
      part2: {   from: 'middle',   to: 'end'  }
      part1: {   from: 'middle',       to: 'start'   }
  }
}
""";

@OptIn(ExperimentalMotionApi::class)
@Preview
@Composable
fun AnimationScreen() {
    val context = LocalContext.current
    val motionScene = remember { motionSceneStr }
    var progress by remember { mutableStateOf(0f) }

    Column {
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = if (progress < 0.5) 1 - progress * 2 else progress * 2 - 1,
            //transition  = if (progress<0.5f) "part1" else "part2",
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .layoutId("row_container")
            ) { }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topEnd = 25.dp,
                            bottomEnd = 25.dp,
                            topStart = 25.dp,
                            bottomStart = 25.dp
                        )
                    )
                    .background(Color.Red)
                    .layoutId("circle")
            )
        }

        Spacer(Modifier.height(32.dp))
        Box(modifier = Modifier.padding(20.dp)) {
            Slider(
                modifier = Modifier,
                value = progress,
                onValueChange = {
                    progress = it
                }
            )
        }

    }
}