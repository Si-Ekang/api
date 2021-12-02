package exposition.config

import io.ktor.application.*
import io.ktor.features.*

fun Application.installCORS() {
    install(CORS) {
        host(host = "0.0.0.0")
        host(host = "herokuapp.com", listOf("https"), listOf("ekang-api"))
        host(host = "localhost")
    }
}
