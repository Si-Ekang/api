package config

import io.ktor.application.*
import io.ktor.features.*

fun Application.installCORS() {
    install(CORS) {
        host("0.0.0.0")
        host(host = "herokuapp.com",
            schemes = listOf("https"),
            subDomains = listOf("ekang-api")
        )
        host("localhost")
    }
}
