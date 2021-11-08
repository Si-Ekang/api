import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.main(testing: Boolean = false) {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true })
    }
    if (!testing) install(CORS) {
        host(host = "0.0.0.0")
        host(host = "herokuapp.com", listOf("https"), listOf("mie-search-api"))
        host(host = "localhost")
    }
    routing {
        static {
            resources(resourcePackage = "openapi")
            resource(remotePath = "/", resource = "openapi/index.html")
        }
    }
}
