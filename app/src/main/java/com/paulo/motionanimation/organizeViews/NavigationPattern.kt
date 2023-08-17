package com.paulo.motionanimation.organizeViews

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

//***************************************************************
// EVENTOS DA TELA (EVENT) -> ABSTRACT FACTORY
//***************************************************************
enum class ScreenEventFactoryPattern {
    LOADING, REGULAR, ERROR
}
//***************************************************************
// DADOS DA TELA (STATE) -> STATE PATTERN
//***************************************************************
data class DataHomeStatePattern(
    val success: List<String> = emptyList(),
    val errorMessage: String = "",
    val event: Event = Event.LOADING
)


//***************************************************************
// NAVIGATION -> ENUM TUNADA -> SINGLETON
//***************************************************************
sealed class ScreenNavigationAbstract(val route: String) {
    object HomeScreen : Screen("home")
    object AllTransactionsScreen : Screen("trans")
    object AddTransactionScreen : Screen("addTras")

}


//***************************************************************
//  DESIGN PATTERN FACTORY
//***************************************************************
 interface NavigationFactoryPattern {
    fun create(navGraphBuilder: NavGraphBuilder, navController: NavController)
}
class HomeScreenFactoryPattern(
    private val paddingValues: PaddingValues = PaddingValues(0.dp)
): NavigationFactoryPattern{
    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(Screen.HomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val homeModelState: HomeModel by homeViewModel.homeState.collectAsState()
            val hideSensitiveDataState: Boolean by homeViewModel.hideSensitiveDataState.collectAsState()

            HomeCompose(
                paddingValues = paddingValues,
                homeModel = homeModelState,
                deleteTransaction = { transactionId ->
                    homeViewModel.deleteTransaction(transactionId)
                },
                hideSensitiveDataState = hideSensitiveDataState,
                changeSensitiveDataVisibility = { visibility ->
                    homeViewModel.changeSensitiveDataVisibility(visibility)
                },
                navigateToAllTransactions = {
                    navController.navigate(Screen.AllTransactionsScreen.route)
                },
                navigateToAddTransaction = {
                    navController.navigate(Screen.AddTransactionScreen.route)
                },
            )
        }
    }
}

//***************************************************************
// VIEW MODEL  MODEL VIEW INTENT
//***************************************************************
class HomeViewModelMvi : ViewModel() {
    private val _hideSensitiveDataState = MutableStateFlow(false)
    private val _homeState = MutableStateFlow(HomeModel())

    val hideSensitiveDataState = _hideSensitiveDataState.asStateFlow()
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _homeState.update {
                it.copy(
                    event = Event.REGULAR
                )
            }
        }
    }


    fun deleteTransaction(transactionId: Long) {

    }

    fun changeSensitiveDataVisibility(visibility: Boolean) {
        _hideSensitiveDataState.updateAndGet { visibility }
        Log.d("CHANGE", "VISIBLE:: ${hideSensitiveDataState.value}")
    }
}

//***************************************************************
//  COMPOSE
//***************************************************************
@Composable
fun HomeCompose(
    paddingValues: PaddingValues,
    homeModel: HomeModel,
    hideSensitiveDataState: Boolean,
    deleteTransaction: (Long) -> Unit = {},
    changeSensitiveDataVisibility: (Boolean) -> Unit = {},
    navigateToAddTransaction: () -> Unit = {},
    navigateToAllTransactions: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (homeModel.event) {
            Event.LOADING -> HomeScreenLoadingCompose()
            Event.ERROR -> HomeScreenErrorCompose()
            Event.REGULAR -> HomeScreenRegularCompose(
                deleteTransaction = deleteTransaction,
                changeSensitiveDataVisibility = changeSensitiveDataVisibility,
                navigateToAddTransaction = navigateToAddTransaction,
                navigateToAllTransactions = navigateToAllTransactions,
                hideSensitiveDataState = hideSensitiveDataState
            )
        }
    }

}

@Composable
fun HomeScreenLoadingCompose() {
    Text(text = "HomeScreenLoading")
}

@Composable
fun ColumnScope.HomeScreenRegularCompose(
    hideSensitiveDataState: Boolean,
    deleteTransaction: (Long) -> Unit = {},
    changeSensitiveDataVisibility: (Boolean) -> Unit = {},
    navigateToAddTransaction: () -> Unit = {},
    navigateToAllTransactions: () -> Unit,
) {


    val hide = remember { hideSensitiveDataState }
    val delete = remember { deleteTransaction }
    val change = remember { changeSensitiveDataVisibility }


    Text(text = "HomeScreenRegular Sensive data: ${hide}")
    Button(onClick = { delete(1L) }) { Text(text = "deleteTransaction") }
    Button(onClick = { change(!hide) }) { Text(text = "changeSensitiveDataVisibility") }
    Button(onClick = navigateToAddTransaction) { Text(text = "navigateToAddTransaction") }
    Button(onClick = navigateToAllTransactions) { Text(text = "navigateToAllTransactions") }
}

@Composable
fun HomeScreenErrorCompose() {
    Text(text = "HomeScreenError")
}