package com.paulo.motionanimation.shopCart.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulo.motionanimation.R
import com.paulo.motionanimation.ui.cards.CardDashDivider
import com.paulo.motionanimation.ui.shape.ReceiptCardBottomWavy
import com.paulo.motionanimation.ui.shape.ReceiptCardLeftWavy
import com.paulo.motionanimation.ui.shape.TicketShape
import com.paulo.motionanimation.ui.shape.drawTicketPath
import com.paulo.motionanimation.ui.shape.flags.SimpleButton
import com.valentinilk.shimmer.shimmer
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Alipay
import compose.icons.fontawesomeicons.brands.Amazon
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Git
import compose.icons.fontawesomeicons.brands.Gitlab
import compose.icons.fontawesomeicons.brands.Instagram
import compose.icons.fontawesomeicons.brands.Linkedin
import compose.icons.fontawesomeicons.brands.Whatsapp
import compose.icons.fontawesomeicons.brands.Youtube
import kotlinx.coroutines.selects.select
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


data class Section(
    val title: String,
    val resource: Int
)

val listOfChip = listOf(

    Section(resource = R.drawable.cat_1, title = "Pizza"),
    Section(resource = R.drawable.cat_2, title = "Hamburger"),
    Section(resource = R.drawable.cat_3, title = "Hot Dog"),
    Section(resource = R.drawable.cat_4, title = "Drink"),
    Section(resource = R.drawable.cat_5, title = "Donut"),
)

val listMidia = listOf(
    FontAwesomeIcons.Brands.Facebook,
    FontAwesomeIcons.Brands.Whatsapp,
    FontAwesomeIcons.Brands.Instagram,
    FontAwesomeIcons.Brands.Linkedin,
    FontAwesomeIcons.Brands.Youtube,
    FontAwesomeIcons.Brands.Amazon,
    FontAwesomeIcons.Brands.Alipay,
    FontAwesomeIcons.Brands.Git,
)

data class Food(
    val name: String,
    val description: String,
    val star: Int,
    val value: Double,
    val icon: Int
)

val foods = listOf(
    Food(
        name = "Pizza de peperony com molho de tare",
        star = 3,
        value = 80.99,
        icon = R.drawable.pizza,
        description = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para "
    ),
    Food(
        name = "Pizza Gourmet",
        star = 3,
        value = 40.99,
        icon = R.drawable.pop_1,
        description = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para "
    ),
    Food(
        name = "X-Egg big top",
        star = 3,
        value = 80.99,
        icon = R.drawable.pop_2,
        description = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para "
    ),
    Food(
        name = "Pizza Big blast mast",
        star = 3,
        value = 90.99,
        icon = R.drawable.pop_3,
        description = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para "
    ),
    Food(
        name = "Pizza de peperony com molho de tare",
        star = 3,
        value = 80.99,
        icon = R.drawable.pizza,
        description = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para "
    ),
    Food(
        name = "Pizza Gourmet",
        star = 3,
        value = 40.99,
        icon = R.drawable.pop_1,
        description = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para "
    ),
    Food(
        name = "X-Egg big top",
        star = 3,
        value = 80.99,
        icon = R.drawable.pop_2,
        description = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para "
    ),
    Food(
        name = "Pizza Big blast mast",
        star = 3,
        value = 90.99,
        icon = R.drawable.pop_3,
        description = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para "
    ),
)

@Preview
@Composable
fun Screen() {
    CollapsingToolbarScaffold(
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        modifier = Modifier
            .fillMaxSize(),
        toolbar = {
            Header()
        },
        body = {
            Content()
        }
    )
}

@Preview
@Composable
fun Content() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.isLoading.collectAsState()


    LaunchedEffect(key1 = Unit, block = {
        viewModel.changeLoading()
    })
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        if (state.value) {
            items(20) {
                Loading.HomeShimmer()
            }
        } else {
            items(foods) {
                val showMore = remember {
                    mutableStateOf(false)
                }
                Box(modifier = Modifier.width(510.dp)) {

                    ReceiptCardBottomWavy {

                        Column(
                            modifier = Modifier
                                .padding(start = 30.dp, top = 20.dp)
                        ) {
                            Text(
                                text = it.name,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 30.dp, bottom = 20.dp)
                            )

                            CardDashDivider()

                            Text(
                                text = it.description,
                                color = Color.White,
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(10.dp)
                            )
                            AnimatedVisibility(visible = showMore.value) {
                                Column {
                                    Spacer(modifier = Modifier.height(15.dp))
                                    CardDashDivider()
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Text(
                                            text = "deslize para o lado >>",
                                            color = Color.LightGray
                                        )
                                    }
                                    LazyRow(
                                        modifier = Modifier.padding(start = 1.dp),
                                        content = {

                                            items(5) {
                                                Box(
                                                    modifier = Modifier
                                                        .padding(8.dp)
                                                        .size(90.dp)
                                                        .background(
                                                            Color.White,
                                                            shape = CutCornerShape(
                                                                topEndPercent = 90,
                                                            ),
                                                        ),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.pop_3),
                                                        contentDescription = "",
                                                        modifier = Modifier.size(80.dp)
                                                    )
                                                }
                                            }
                                        })
                                    Spacer(modifier = Modifier.height(15.dp))
                                    CardDashDivider()
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                            .background(Color.Red, shape = RoundedCornerShape(10.dp))
                                            .clip(RoundedCornerShape(10.dp))
                                            .clickable { },
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {

                                        Icon(
                                            Icons.TwoTone.ShoppingCart,
                                            contentDescription = null,
                                            tint = Color.White
                                        )

                                        Text(
                                            text = "Adicionar ao carrinho",
                                            color = Color.White,
                                            fontSize = 15.sp,
                                            modifier = Modifier
                                                .padding(15.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(15.dp))
                                    CardDashDivider()
                                    Spacer(modifier = Modifier.height(15.dp))
                                }


                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier
                                    .weight(1f)
                                    .wrapContentSize()
                                    .graphicsLayer {
                                        shadowElevation = 8.dp.toPx()
                                        shape = TicketShape(5.dp.toPx())
                                        clip = true
                                    }
                                    .background(color = Color.White)
                                    .padding(10.dp)) {
                                    Text(
                                        text = "R$: ${it.value}",
                                        color = Color.Black,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }


                                TextButton(onClick = {
                                    showMore.value = !showMore.value
                                },
                                    modifier = Modifier.weight(2f)
                                ) {
                                    Text(text = if (showMore.value) "Fechar" else " Adicionar", color = Color.Cyan)
                                }



                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(90))
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Star, contentDescription = null, tint = Color.DarkGray, modifier = Modifier.size(40.dp))
                                    Text(text = "${it.star} / 5", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 6.sp)
                                }

                            }
                        }

                    }
                    Image(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.name,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
        }

    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun Header() {

    val height = 210.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color.Transparent),
    ) {

        val query = remember {
            mutableStateOf("")
        }
        val visibleChecked = remember {
            mutableStateOf(false)
        }

        Image(
            painter = painterResource(id = R.drawable.background_intro),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_verified_24),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(80))
                        .background(Color.Black)
                        .clickable {
                            visibleChecked.value = !visibleChecked.value
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("The best place", color = Color.Black, modifier = Modifier.padding(start = 20.dp))
            }
            TextField(
                value = query.value,
                onValueChange = {
                    query.value = it
                },
                label = {
                    Text(text = "Search", color = Color.LightGray)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.TwoTone.Search,
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                },
                modifier = Modifier
                    .width(380.dp)
                    .clip(RoundedCornerShape(10.dp)),
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFF142730),
                    unfocusedIndicatorColor = Color.White
                )
            )
            LazyRow(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(listOfChip) {
                    val active = remember {
                        mutableStateOf(false)
                    }

                    CustomImageChip(
                        text = it.title,
                        imageId = it.resource,
                        selected = active.value,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                active.value = !active.value
                            }
                    )


                }
            }
        }

        Text(
            text = "ShopCart",
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 70.dp))
                .background(Color.Black)
                .padding(end = 15.dp),
            fontSize = 23.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold

        )

        AnimatedVisibility(
            visible = visibleChecked.value,
            enter = slideInVertically(
                initialOffsetY = { -40 }
            ) + expandVertically(
                expandFrom = Alignment.Top
            ) + scaleIn(
                // Animate scale from 0f to 1f using the top center as the pivot point.
                transformOrigin = TransformOrigin(0.5f, 0f)
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(targetScale = 1.2f)
        ) {
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp
                        )
                    )
                    .background(Color.Black.copy(alpha = .9f))
                    .fillMaxWidth()
                    .height(height)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Nossas Midias",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(3f)
                    )
                    IconButton(onClick = {
                        visibleChecked.value = !visibleChecked.value
                    }) {
                        Icon(
                            imageVector = Icons.TwoTone.Clear,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }


                LazyVerticalGrid(
                    columns = GridCells.Fixed(4)
                ) {
                    items(listMidia) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

    }
}

/*
 Row(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            IconButton(onClick = {
                                                if(count.value.isEmpty()){
                                                    count.value = "1"
                                                }else{
                                                    val c = count.value.toInt() +1
                                                    count.value = c.toString()
                                                }

                                            }) {
                                                Icon(
                                                    painterResource(id = R.drawable.baseline_exposure_plus_1_24),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(80.dp)
                                                        .background(Color.Red, shape = RoundedCornerShape(10))
                                                        .clickable { }
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .background(Color.White, shape = RoundedCornerShape(10))
                                                    .size(60.dp, 40.dp),
                                                contentAlignment = Alignment.Center
                                            ){
                                                Text(text =count.value, fontWeight = FontWeight.Bold)
                                            }
                                            IconButton(onClick = {
                                                if(count.value.isEmpty()){
                                                    count.value = ""
                                                }else{
                                                    val c = count.value.toInt() -1
                                                    count.value = c.toString()
                                                }
                                            }) {
                                                Icon(
                                                    painterResource(id = R.drawable.baseline_exposure_neg_1_24),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(80.dp)
                                                        .background(Color.Red, shape = RoundedCornerShape(10))
                                                        .clickable { }
                                                )
                                            }
                                        }
 */