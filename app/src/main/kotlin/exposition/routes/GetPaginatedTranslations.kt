package exposition.routes

import core.models.*
import core.types.PaginationSize
import core.types.StrictlyPositiveInt
import core.types.toPaginationSize
import core.types.toStrictlyPositiveInt
import exposition.types.HandlerContext
import exposition.types.classLoader
import exposition.types.getQueryParameterAs
import infrastructure.getTranslationsFromFileAsync
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.http.HttpStatusCode.Companion.NotImplemented
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.coroutineScope

suspend fun HandlerContext.getPaginatedTranslations(testing: Boolean = false) =
    if (application.developmentMode || testing) devImplementation()
    else call.respond(NotImplemented)

private suspend fun HandlerContext.devImplementation() {
    val translations: Set<Translation> = coroutineScope {
        val lines: Deferred<List<Map<String, String>>> =
            getTranslationsFromFileAsync(classLoader)
        val pagination: Pagination = getPaginationFromQueryParameters()
        lines.await() toPaginatedTranslations pagination
    }
    if (translations.isEmpty()) call.respond(NoContent)
    else call.respond(OK, translations)
}

private fun HandlerContext.getPaginationFromQueryParameters(): Pagination {
    val page: StrictlyPositiveInt =
        getQueryParameterAs(name = "page", String::toStrictlyPositiveInt)
    val size: PaginationSize =
        getQueryParameterAs(name = "size", String::toPaginationSize)
    return page withSize size
}

private infix fun List<Map<String, String>>.toPaginatedTranslations(
    pagination: Pagination
): Set<Translation> = subList(pagination)
    .map { it.toTranslation() ?: error("Translation creation failed.") }
    .toSet()
