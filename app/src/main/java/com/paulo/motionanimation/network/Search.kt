package com.paulo.motionanimation.network
/*
fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query

}
/**
 * Simulation of network data
 */
private fun dataFromNetwork(query: String): Flow<String> {
    return flow {
        delay(2000)
        emit(query)
    }
}
searchView.getQueryTextChangeStateFlow()
.debounce(300)
.filter { query ->
    if (query.isEmpty()) {
        textViewResult.text = ""
        return@filter false
    } else {
        return@filter true
    }
}
.distinctUntilChanged()
.flatMapLatest { query ->
    dataFromNetwork(query)
        .catch {
            emitAll(flowOf(""))
        }
}
.flowOn(Dispatchers.Default)
.collect { result ->
    textViewResult.text = result
}
Entendendo os Operadores
Debounce: Aqui, o debounceoperador é usado com uma constante de tempo.
O debounceoperador lida com o caso quando o usuário digita “a”, “ab”,
“abc”, em um tempo muito curto. Portanto, haverá tantas chamadas de rede.
Mas o usuário está finalmente interessado no resultado da pesquisa “abc”.
Portanto, devemos descartar os resultados de “a”e “ab”. Idealmente,
não deve haver chamadas de rede para “a”e “ab”como o usuário as digitou
em um tempo muito curto. Então, o debounceoperador vem em socorro.
Odebounceaguardará o tempo fornecido para fazer qualquer coisa, se
qualquer outra consulta de pesquisa ocorrer entre esse tempo, ele
ignorará o item anterior e começará a aguardar esse tempo novamente
com a nova consulta de pesquisa. Se nada de novo aparecer nesse tempo
constante, ele prosseguirá com essa consulta de pesquisa para
processamento posterior. Portanto, debouncesó emita um item, se um
intervalo de tempo específico tiver passado sem que ele emita outro item.

Filtro: O filteroperador é usado para filtrar a string indesejada
como uma string vazia neste caso para evitar a chamada de rede desnecessária.

DistinctUntilChanged: O distinctUntilChangedoperador é usado para
evitar chamadas de rede duplicadas. Digamos que a última consulta
de pesquisa em andamento foi “abc”e o usuário excluiu “c”e digitou novamente
“c”. Então, novamente é “abc”. Portanto, se a chamada de rede já estiver em
andamento com a consulta de pesquisa “abc”, ela não fará a chamada duplicada
novamente com a consulta de pesquisa “abc”. Portanto, distinctUntilChangedsuprima
itens consecutivos duplicados emitidos pela fonte.

FlatMapLatest: Aqui, o flatMapLatestoperador é usado para evitar os
resultados da chamada de rede que não são mais necessários para
exibição ao usuário. Digamos que a última consulta de pesquisa foi
“ab”e há uma chamada de rede em andamento para “ab”e o usuário digitou
“abc”. Então, não estamos mais interessados ​​no resultado de “ab”.
Estamos interessados ​​apenas no resultado de “abc”. Então, o
flatMapLatestvem para o resgate. Ele fornece apenas o resultado da última
consulta de pesquisa (mais recente) e ignora o restante.
 */



