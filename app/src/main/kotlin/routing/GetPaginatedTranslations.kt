package routing

import arrow.core.Option
import arrow.core.computations.either
import arrow.core.getOrNone
import arrow.core.toOption
import domain.Translation
import domain.toIntAfter
import domain.toTranslation
import infrastructure.csvReader
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.*
import io.ktor.util.pipeline.*
import java.io.File
import kotlin.system.measureTimeMillis

private val PipelineContext<Unit, ApplicationCall>.classLoader: ClassLoader
    get() = application.environment.classLoader

suspend fun PipelineContext<Unit, ApplicationCall>.getPaginatedTranslations() {
    measureTimeMillis {
        either<HttpResponse<String>, List<Translation>> {
            (mapOf("page" to 0, "size" to 9)
                .mapValues {
                    call.request.queryParameters getOrNone it.key to it.value
                }.mapValues {
                    it.value.first.toEither {
                        BadRequest toHttpResponse "${it.key} parameter not found."
                    } to it.value.second
                }.mapValues { it.value.first.bind() to it.value.second }
                .mapValues { it.value.first.toInt() to it.value.second }
                .mapValues { it.value.first toIntAfter it.value.second }
                .mapValues {
                    it.value.toEither {
                        BadRequest toHttpResponse "Given ${it.key} is invalid."
                    }
                }.mapValues {
                    it.value.bind()
                } to classLoader.getResource("translations.csv")
                .toOption()
                .toEither {
                    val body = "Translations file not found."
                    InternalServerError toHttpResponse body
                }.bind()
                .path.let(::File)
                .let(csvReader::readAllWithHeader)
                .map { it.toTranslation() }
                .map {
                    it.toEither {
                        val body = "Translations parsing failed."
                        InternalServerError toHttpResponse body
                    }
                }.map { it.bind() }
                .toSet())
                .run { first to second.toList() }
                .run {
                    val page: Int = first.getOrNone(key = "page")
                        .toEither {
                            InternalServerError toHttpResponse "Page not found."
                        }.bind().value
                    val size: Int = first.getOrNone(key = "size")
                        .toEither {
                            InternalServerError toHttpResponse "Size not found."
                        }.bind().value
                    val fromIndex: Int = (page - 1) * size
                    val toIndex: Int = page * size
                    if (fromIndex >= second.size) emptyList()
                    else if (toIndex > second.size)
                        second.subList(fromIndex, second.size)
                    else second.subList(fromIndex, toIndex)
                }.takeIf(List<Translation>::isNotEmpty)
                .toOption()
                .toEither {
                    val body = "No translation available for this page."
                    NoContent toHttpResponse body
                }.bind()
        }.fold({
            if (it.code == InternalServerError) {
                call.respond(it.code)
                application.log.error(it.body)
            } else call.respond(it.code, it.body)
        }) { call.respond(OK, it) }
    }.let { application.log.info("Completed in $it ms.") }
}

private infix fun <T> HttpStatusCode.toHttpResponse(body: T): HttpResponse<T> =
    HttpResponse(code = this, body)

private infix fun Parameters.getOrNone(name: String): Option<String> = get(name)
    .toOption()

private data class HttpResponse<T>(val code: HttpStatusCode, val body: T)
