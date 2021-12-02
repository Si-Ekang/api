import exposition.config.installContentNegotiation
import exposition.config.installStatusPages
import exposition.routes.getPaginatedTranslations
import exposition.routes.serveDocs
import exposition.types.handle
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.main() {
    installContentNegotiation()
    installCORS()
    installStatusPages()
    routing {
        serveDocs()
        get(path = "${Version.V0}/translations") {
            handle { getPaginatedTranslations() }
        }
    }
}

private fun Application.installCORS() {
    install(CORS) {
        host(host = "0.0.0.0")
        host(host = "herokuapp.com", listOf("https"), listOf("ekang-api"))
        host(host = "localhost")
    }
}

enum class Version {
    V0;

    override fun toString(): String = name.lowercase()
}
