import config.installCORS
import config.installContentNegotiation
import io.ktor.application.*
import io.ktor.routing.*
import routing.Version
import routing.docs
import routing.questions

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(testing: Boolean = false) {
    installContentNegotiation()
    if (!testing) installCORS()
    routing {
        docs()
        route(Version.V0.toString(), Route::questions)
    }
}
