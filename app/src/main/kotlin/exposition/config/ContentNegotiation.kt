package exposition.config

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun Application.installContentNegotiation() {
    install(ContentNegotiation) {
        val instance = Json { prettyPrint = true }
        json(instance)
    }
}
