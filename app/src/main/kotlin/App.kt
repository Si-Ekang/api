import config.installCORS
import config.installContentNegotiation
import io.ktor.application.*
import io.ktor.routing.*
import routing.docs

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(testing: Boolean = false) {
    installContentNegotiation()
    if (!testing) installCORS()
    routing(Routing::docs)
}
