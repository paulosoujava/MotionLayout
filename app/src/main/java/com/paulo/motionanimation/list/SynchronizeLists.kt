package com.paulo.motionanimation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadhamwi.tabsync_compose.lazyListTabSync

class Item(val content: String) {

}

class Category(val name: String, vararg item: Item) {

    val listOfItems: List<Item> = item.toList()

}

 val categoriesList = listOf(
    Category(
        "Category 1",
        Item("Item 1"),
        Item("Item 2"),
        Item("Item 3"),
        Item("Item 4"),
        Item("Item 5"),
        Item("Item 6")
    ),
    Category(
        "Category 5",
        Item("Item 1"),
        Item("Item 2"),
        Item("Item 4"),
        Item("Item 5"),
    ),
)



@Preview
@Composable
fun MyTabBar(
    categories: List<Category> = categoriesList,
    selectedTabIndex: Int = 1,
    //onTabClicked: (index: Int, category: Category) -> Unit
) {
    /*
     val (selectedTabIndex, setSelectedTabIndex, lazyListState) = tabSyncMediator(
        mutableListOf(0, 2, 4), //Mandatory. The indices of lazy list items to sync the tabs with
        tabsCount = 3, //Optional. To check for viability of the synchronization with the tabs. Optimal when equals the count of syncedIndices.
        lazyListState = rememberLazyListState(), //Optional. To provide your own LazyListState. Defaults to rememberLazyListState().
        smoothScroll = true, // Optional. To make the auto scroll smooth or not when clicking tabs. Defaults to true
    )
     */
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { },//onTabClicked(index, category) },
                text = { Text(category.name.uppercase()) }
            )
        }
    }
}

@Preview
@Composable
fun TabSyncComposeScreen(categories: List<Category> =categoriesList ) {
    val (selectedTabIndex, setSelectedTabIndex, lazyListState) = lazyListTabSync(categories.indices.toList())

    Column {
        MyTabBar(
            categories = categories,
            selectedTabIndex = selectedTabIndex,
         //   onTabClicked = { index, _ -> setSelectedTabIndex(index) }
        )

        MyLazyList(categories, lazyListState)
    }
}
@Composable
fun MyLazyList(
    categories: List<Category>,
    listState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(categories) { _, category ->
            ItemCategory(category)
        }
    }
}

@Composable
fun ItemCategory(category: Category) {
    Text(text = category.name)
}
