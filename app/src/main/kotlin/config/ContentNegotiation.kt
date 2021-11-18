package config

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun Application.installContentNegotiation() {
    install(ContentNegotiation, ContentNegotiation.Configuration::configure)
}

private fun ContentNegotiation.Configuration.configure() {
    val instance = Json { prettyPrint = true }
    json(instance)
}
