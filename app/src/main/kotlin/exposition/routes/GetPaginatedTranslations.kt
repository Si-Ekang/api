package exposition.routes

import core.types.PaginationSize
import core.types.StrictlyPositiveInt
import core.types.toPaginationSize
import core.types.toStrictlyPositiveInt
import exposition.types.HandlerContext
import exposition.types.getQueryParameterAs
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.NotImplemented
import io.ktor.response.*

suspend fun HandlerContext.getPaginatedTranslations() {
    if (application.developmentMode) {
        val page: StrictlyPositiveInt =
            getQueryParameterAs(name = "page", String::toStrictlyPositiveInt)
        val size: PaginationSize =
            getQueryParameterAs(name = "size", String::toPaginationSize)
        application.log.debug("page = $page and size = $size")
    }
    call.respond(NotImplemented)
}
