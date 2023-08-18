package com.paulo.motionanimation.shopCart.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.motionanimation.ui.cards.CardDashDivider
import com.paulo.motionanimation.ui.shape.ReceiptCardBottomWavy
import com.valentinilk.shimmer.shimmer

object Loading {
    @Composable
    fun Shimmer() {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
                .shimmer(),
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(84.dp)
                        .background(Color.Gray)
                )
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(230.dp, 24.dp)
                            .background(Color.Gray)
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(340.dp, 24.dp)
                            .background(Color.Gray)
                    )
                }

            }

        }
    }

    @Composable
    fun HomeShimmer() {
        Box(modifier = Modifier.size(400.dp, 200.dp).shimmer()) {

            ReceiptCardBottomWavy {

                Column(
                    modifier = Modifier
                        .size(400.dp, 250.dp)
                        .padding(start = 30.dp, top = 20.dp)
                ) {
                    Box(
                        modifier = Modifier.shimmer()
                            .background(Color.Gray)
                            .size(145.dp, 20.dp).padding(start = 30.dp, bottom = 10.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    CardDashDivider()
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.shimmer().background(Color.Gray).size(265.dp, 20.dp).padding(10.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.shimmer().background(Color.Gray).size(45.dp, 20.dp),
                        )
                        Spacer(Modifier.width(150.dp))
                        Box(modifier = Modifier.shimmer().background(Color.Gray).size(45.dp, 20.dp))
                        Spacer(Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(90))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Box( modifier = Modifier.shimmer().background(Color.Gray).size(40.dp))
                        }

                    }
                }

            }
        }

    }

}
