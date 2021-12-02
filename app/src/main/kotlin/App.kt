import exposition.config.installCORS
import exposition.config.installContentNegotiation
import exposition.config.installStatusPages
import exposition.routes.getPaginatedTranslations
import exposition.routes.serveDocs
import exposition.types.handle
import io.ktor.application.*
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

private enum class Version {
    V0;

    override fun toString(): String = name.lowercase()
}
