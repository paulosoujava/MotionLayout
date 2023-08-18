package com.paulo.motionanimation.shopCart.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulo.motionanimation.R


// Function for Custom Chip
// Later we will reuse it to
// create chips wherever needed
// selected : Boolean - to Check
// whether it is checked or not
// text - To show the data
@Composable
fun CustomChip(
    selected: Boolean,
    text: String,
    modifier: Modifier = Modifier
) {
    // define properties to the chip
    // such as color, shape, width
    Surface(
        color = when {
            selected -> MaterialTheme.colorScheme.onSurface
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colorScheme.onPrimary
            else -> Color.LightGray
        },
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colorScheme.primary
                else -> Color.LightGray
            }
        ),
        modifier = modifier
    ) {
        // Add text to show the data that we passed
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp)
        )

    }
}

// Function to create a Custom Image Chip with text
// text - For showing data on the chip
// imageId - For showing the image that we want to use
// selected : Boolean - to check if it is selected or not
@Composable
fun CustomImageChip(
    text: String,
    imageId: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    // define properties to the chip
    // such as color, shape, width
    Surface(
        color = when {
            selected -> Color.Black
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colorScheme.onPrimary
            else -> Color.Black
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colorScheme.primary
                else -> Color.Black
            }
        ),
        modifier = modifier
    ) {
        // Inside a Row pack the Image and text together to
        // show inside the chip
        Row(modifier = Modifier) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)
                    .clip(CircleShape)
            )
            Text(
                text = text,
                style = typography.bodyMedium,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun Chips() {
    Text(text = "Custom Chips", style = typography.bodyMedium, modifier = Modifier.padding(8.dp))
    SubtitleText(subtitle = "Custom chips with surface")
    // Call the functions that we defied
    // above and pass the actual data
    Column {
        Row(modifier = Modifier.padding(8.dp)) {
            // creates a custom chip for active state
            CustomChip(
                selected = true,
                text = "Chip",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            // Creates a custom chip for inactive state
            CustomChip(
                selected = false,
                text = "Inactive",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            // Create a custom image chip whose state is active
            CustomImageChip(
                text = "custom",
                imageId = R.drawable.cat_1,
                selected = true
            )
            Spacer(modifier = Modifier.padding(8.dp))
            // Create a custom image chip whose state is inactive
            CustomImageChip(
                text = "custom2",
                imageId = R.drawable.cat_2,
                selected = false
            )
        }
        SubtitleText(subtitle = "Buttons with circle clipping.")
        // We can also use a circular shape button as a chip
        Row(modifier = Modifier.padding(8.dp)) {
            // Creates an active state chip using button
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
            ) {
                Text(text = "Chip button")
            }
            // Creates an inactive state chip using button
            Button(
                onClick = {},
                enabled = false,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
            ) {
                Text(text = "Disabled chip")
            }
        }
    }
}

// Function to show a text message
@Composable
fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    Text(text = subtitle, style = typography.bodySmall, modifier = modifier.padding(8.dp))
}