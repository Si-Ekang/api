import common.measure
import config.installCORS
import config.installContentNegotiation
import config.installStatusPages
import io.ktor.application.*
import io.ktor.routing.*
import translations.getPaginatedTranslations

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.main() {
    installContentNegotiation()
    installCORS()
    installStatusPages()
    routing {
        get(path = "${Version.V1}/translations") {
            measure { getPaginatedTranslations() }
        }
    }
}

private enum class Version {
    V1;

    override fun toString(): String = name.lowercase()
}