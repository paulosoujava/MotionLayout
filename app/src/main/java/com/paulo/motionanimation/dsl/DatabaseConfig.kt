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

fun main() {
    val config = databaseConfig {
        host = "localhost"
        port = "5432"
        username = "postgres"
        password = "postgres"
    }
    println(config)
}