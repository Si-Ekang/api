import categories.handlers.getCategories
import categories.handlers.getTranslationsOfCategory
import common.ID_PARAM
import common.measure
import config.installCORS
import config.installContentNegotiation
import config.installStatusPages
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.netty.*
import translations.handlers.getPaginatedTranslations

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.main() {
    installContentNegotiation()
    installCORS()
    installStatusPages()
    routing {
        route("v1") {
            route("categories") {
                get {
                    measure { getCategories() }
                }
                get("{$ID_PARAM}/translations") {
                    measure { getTranslationsOfCategory() }
                }
            }
            get("translations") {
                measure { getPaginatedTranslations() }
            }
        }
    }
}
