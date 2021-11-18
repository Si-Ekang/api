package config

import io.ktor.application.*
import io.ktor.features.*

fun Application.installCORS() {
    install(CORS, CORS.Configuration::configure)
}

private fun CORS.Configuration.configure() {
    host(host = "0.0.0.0")
    host(host = "herokuapp.com", listOf("https"), listOf("ekang-api"))
    host(host = "localhost")
}
