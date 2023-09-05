package com.paulo.motionanimation.dsl

data class DatabaseConfig(
    val host: String,
    val port: String,
    val username: String,
    val password: String
)

class DatabaseConfigBuilder {
    var host: String = ""
    var port: String = ""
    var username: String = ""
    var password: String = ""

    fun build(): DatabaseConfig {
        return DatabaseConfig(port, host, username, password)
    }
}

fun databaseConfig(block: DatabaseConfigBuilder.() -> Unit): DatabaseConfig {
    val builder = DatabaseConfigBuilder()
    builder.block()
    return builder.build()
}
inline fun measureTime(block: () -> Unit) {
    val start = System.currentTimeMillis()
    block()
    val endTime = System.currentTimeMillis()
    println("Time taken: ${endTime - start}ms")
}

fun main() {
    val config = databaseConfig {
        host = "localhost"
        port = "5432"
        username = "postgres"
        password = "postgres"
    }
    println(config)

    measureTime {
        for (i in 1..10000000) {
            println(i)
        }
    }
}
/*
DSL(Domain-Specific Languages). Se você está em busca
de simplificar o seu código e tornar suas APIs mais legíveis
 e expressivas, as DSLs podem ser o seu melhor amigo.

O que são DSLs em Kotlin?
As DSLs em Kotlin são uma forma de criar linguagens específicas de domínio,
 que permitem expressar conceitos e estruturas relacionados a um domínio
  específico de forma mais natural e intuitiva.
Um dos exemplos é a DSL de construção de interfaces gráficas do Jetpack
Compose ou também a própria biblioteca de testes unitários Mockk.
 */

