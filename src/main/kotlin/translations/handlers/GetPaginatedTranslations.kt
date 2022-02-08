package translations.handlers

import common.HandlerContext
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import translations.models.*

suspend fun HandlerContext.getPaginatedTranslations(): Unit =
    readTranslationsFile(pagination)
        .takeIf(Set<Translation>::isNotEmpty)
        ?.let { call.respond(HttpStatusCode.OK, it) }
        ?: call.respond(HttpStatusCode.NoContent)
