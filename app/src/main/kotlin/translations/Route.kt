package translations

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getTranslationsWithPagination() {
    get(path = "translations") { call.respond(HttpStatusCode.NotImplemented) }
}
