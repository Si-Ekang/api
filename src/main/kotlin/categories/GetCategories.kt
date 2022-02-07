package categories

import common.HandlerContext
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend fun HandlerContext.getCategories(): Unit =
    call.respond(HttpStatusCode.NotImplemented) // TODO: Not implemented yet
