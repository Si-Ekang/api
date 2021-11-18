package routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.questions() {
    get(path = "questions") { call.respond(HttpStatusCode.NotImplemented) }
}
