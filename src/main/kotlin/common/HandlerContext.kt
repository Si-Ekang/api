package common

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.util.pipeline.*
import kotlin.system.measureTimeMillis

typealias HandlerContext = PipelineContext<Unit, ApplicationCall>

@Throws(BadRequestException::class)
fun <T> HandlerContext.getQueryParameterAs(name: String, map: String.() -> T?):
        T = getQueryParameter(name)
    .map()
    ?: throw BadRequestException("Request parameter $name is invalid.")

inline infix fun HandlerContext.measure(handler: HandlerContext.() -> Unit) {
    val httpUri = "${call.request.httpMethod.value} ${call.request.uri}"
    application.log.info("Processing $httpUri...")
    val time: Long = measureTimeMillis { handler() }
    application.log.info("$httpUri completed in $time ms.")
}

@Throws(MissingRequestParameterException::class)
private infix fun HandlerContext.getQueryParameter(name: String): String =
    call.request.queryParameters[name]
        ?: throw MissingRequestParameterException(name)
