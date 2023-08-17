package com.paulo.motionanimation.organizeViews

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Objects

internal interface ComposeNavigationFactory {
    fun create(navGraphBuilder: NavGraphBuilder, navController: NavController)
}

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object AllTransactionsScreen : Screen("trans")
    object AddTransactionScreen : Screen("addTras")

}

class HomScreenFactory(private val paddingValues: PaddingValues) : ComposeNavigationFactory {
    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(Screen.HomeScreen.route) {

            val homeViewModel = hiltViewModel<MyViewModel>()
            val homeModelState: HomeModel by homeViewModel.homeState.collectAsState()
            val hideSensitiveDataState: Boolean by homeViewModel.hideSensitiveDataState.collectAsState()


            HomeScreen(paddingValues = paddingValues,
                homeModel= homeModelState,
                deleteTransaction = { transactionId ->
                    homeViewModel.deleteTransaction(transactionId)
                },
                hideSensitiveDataState = hideSensitiveDataState,
                changeSensitiveDataVisibility = { visibility ->
                    homeViewModel.changeSensitiveDataVisibility(
                        visibility
                    )
                },
                navigateToAllTransactions = {
                    navController.navigate(Screen.AllTransactionsScreen.route)
                },
                navigateToAddTransaction = {
                    navController.navigate(Screen.AddTransactionScreen.route)
                },)
        }
    }
}





data class HomeModel(
     val success: List<String> = emptyList(),
     val errorMessage: String = "",
     val event: Event = Event.LOADING
)


enum class Event {
    LOADING, REGULAR, ERROR
}

class MyViewModel: ViewModel() {

    val hideSensitiveDataState = MutableStateFlow(false)
    val homeState = MutableStateFlow(HomeModel())


    fun deleteTransaction(transactionId: Long) {

    }

    fun changeSensitiveDataVisibility(visibility: Boolean) {

    }


}