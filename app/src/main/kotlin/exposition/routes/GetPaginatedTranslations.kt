package exposition.routes

import exposition.types.HandlerContext
import exposition.types.getQueryParameterAsIntIf
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.NotImplemented
import io.ktor.response.*

suspend fun HandlerContext.getPaginatedTranslations() {
    if (application.developmentMode) {
        val page: Int = getQueryParameterAsIntIf(name = "page") { it > 0 }
        val size: Int =
            getQueryParameterAsIntIf(name = "size") { it in 10..50 }
        application.log.debug("page = $page and size = $size")
    }
    call.respond(NotImplemented)
}
