package exposition.types

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.util.pipeline.*
import kotlin.system.measureTimeMillis

typealias HandlerContext = PipelineContext<Unit, ApplicationCall>

@Throws(BadRequestException::class)
fun HandlerContext.getQueryParameterAsIntIf(
    name: String,
    condition: (Int) -> Boolean
): Int = getQueryParameter(name)
    .toIntOrNull()
    ?.takeIf(condition)
    ?: throw BadRequestException("Request parameter $name is invalid.")

suspend fun HandlerContext.handle(block: suspend HandlerContext.() -> Unit) {
    val httpUri = "${call.request.httpMethod.value} ${call.request.uri}"
    application.log.info("Processing $httpUri...")
    val time: Long = measureTimeMillis { block() }
    application.log.info("$httpUri completed in $time ms.")
}

@Throws(MissingRequestParameterException::class)
private infix fun HandlerContext.getQueryParameter(name: String): String =
    call.request.queryParameters[name]
        ?: throw MissingRequestParameterException(name)