package com.paulo.motionanimation.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/*

interface ApiHelper {
    abstract fun getUsers(): Any
    abstract fun getMoreUsers(): Any

}

interface DatabaseHelper {

}

sealed class UiState {
    object Loading : UiState()
    class Error(toString: Any) : UiState()
    class Success(it: Any) : UiState()
}

data class ApiUser(val name: String)


class ParallelNetworkCallsViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.Loading)

    val uiState = _uiState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = UiState.Loading
            apiHelper.getUsers()
                .zip(apiHelper.getMoreUsers()) { usersFromApi, moreUsersFromApi ->
                    val allUsersFromApi = mutableListOf<ApiUser>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)
                    return@zip allUsersFromApi
                }
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}
*/