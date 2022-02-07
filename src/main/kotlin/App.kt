import common.measure
import config.installCORS
import config.installContentNegotiation
import config.installStatusPages
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.netty.*
import translations.getPaginatedTranslations

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.main() {
    installContentNegotiation()
    installCORS()
    installStatusPages()
    routing {
        get("v1/translations") {
            measure { getPaginatedTranslations() }
        }
    }
}
