package routing

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.HttpStatusCode.Companion.NotImplemented
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import kotlin.system.measureTimeMillis

private typealias Context = PipelineContext<Unit, ApplicationCall>

private val Context.httpUri: String
    get() = "${call.request.httpMethod.value} ${call.request.uri}"

suspend fun Context.getPaginatedTranslations() {
    val time: Long = measureTimeMillis {
        if (application.developmentMode) {
            val page: Int = getQueryParameterAsIntIf(name = "page") { it > 0 }
            val size: Int =
                getQueryParameterAsIntIf(name = "size") { it in 10..50 }
            application.log.debug("page = $page and size = $size")
        }
        call.respond(NotImplemented)
    }
    application.log.info("$httpUri completed in $time ms.")
}

@Throws(MissingRequestParameterException::class)
private infix fun Context.getQueryParameter(name: String): String =
    call.request.queryParameters[name]
        ?: throw MissingRequestParameterException(name)

@Throws(BadRequestException::class)
private inline fun Context.getQueryParameterAsIntIf(
    name: String,
    condition: (Int) -> Boolean
): Int = getQueryParameter(name)
    .toIntOrNull()
    ?.takeIf(condition)
    ?: throw BadRequestException("Request parameter $name is invalid.")
