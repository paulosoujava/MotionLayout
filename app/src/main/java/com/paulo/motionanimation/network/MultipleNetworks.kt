package com.paulo.motionanimation.network

/*
class ParallelNetworkCallsViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                // coroutineScope is needed, else in case of any network error, it will crash
                coroutineScope {
                    val usersFromApiDeferred = async { apiHelper.getUsers() }
                    val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }

                    val usersFromApi = usersFromApiDeferred.await()
                    val moreUsersFromApi = moreUsersFromApiDeferred.await()

                    val allUsersFromApi = mutableListOf<ApiUser>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)

                    uiState.postValue(UiState.Success(allUsersFromApi))
                }
            } catch (e: Exception) {
                uiState.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }

}

Vamos dar outro exemplo.

Suponha que temos duas funções como abaixo:

doLongRunningTaskOne()
doLongRunningTaskTwo()

private suspend fun doLongRunningTaskOne(): Int {
    return withContext(Dispatchers.Default) {
        // your code for doing a long running task
        // Added delay to simulate
        delay(2000)
        return@withContext 10
    }
}

private suspend fun doLongRunningTaskTwo(): Int {
    return withContext(Dispatchers.Default) {
        // your code for doing a long running task
        // Added delay to simulate
        delay(2000)
        return@withContext 10
    }
}

Neste caso, como precisamos recuperar o resultado das tarefas,
usaremos asyncpara lançar a co-rotina.

Assim, podemos executar as duas tarefas em paralelo usando Kotlin Coroutines como abaixo:

fun startLongRunningTaskInParallel() {
    viewModelScope.launch {
        val resultOneDeferred = async { doLongRunningTaskOne() }
        val resultTwoDeferred = async { doLongRunningTaskTwo() }
        val combinedResult = resultOneDeferred.await() + resultTwoDeferred.await()
    }
}

Vamos dar outro exemplo, suponha que temos duas funções que fazem a tarefa e não retornam nada.

private suspend fun doLongRunningTaskOne() {
    withContext(Dispatchers.Default) {
        // your code for doing a long running task
        // Added delay to simulate
        delay(2000)
    }
}

private suspend fun doLongRunningTaskTwo() {
    withContext(Dispatchers.Default) {
        // your code for doing a long running task
        // Added delay to simulate
        delay(2000)
    }
}
Neste caso, como não precisamos recuperar o resultado das tarefas,
usaremos launchpara lançar a co-rotina.
Assim, podemos executar as duas tarefas em paralelo usando Kotlin Coroutines como abaixo:

fun startLongRunningTaskInParallel() {
    viewModelScope.launch {
        launch { doLongRunningTaskOne() }
        launch { doLongRunningTaskTwo() }
    }
}



 */