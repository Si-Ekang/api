import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import routing.getPaginatedTranslations
import routing.serveDocs

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(testing: Boolean = false) {
    install(ContentNegotiation) {
        val instance = Json { prettyPrint = true }
        json(instance)
    }
    install(StatusPages) {
        exception<BadRequestException> {
            call.respond(BadRequest, it.localizedMessage)
        }
        exception<Throwable> { call.respond(InternalServerError) }
    }
    if (!testing) install(CORS) {
        host(host = "0.0.0.0")
        host(host = "herokuapp.com", listOf("https"), listOf("ekang-api"))
        host(host = "localhost")
    }
    routing {
        serveDocs()
        get(path = "${Version.V0}/translations") {
            getPaginatedTranslations()
        }
    }
}

enum class Version {
    V0;

    override fun toString(): String = name.lowercase()
}
