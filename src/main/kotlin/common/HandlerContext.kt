package common

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.util.pipeline.*
import kotlin.system.measureTimeMillis

typealias HandlerContext = PipelineContext<Unit, ApplicationCall>

const val ID_PARAM: String = "id"

@Throws(BadRequestException::class)
fun HandlerContext.getIdFromPath(): Int = getPathParameter(ID_PARAM)
    .toIntOrNull()
    ?.takeIf { it > 0 }
    ?: throw BadRequestException("Request parameter $ID_PARAM is invalid.")

@Throws(MissingRequestParameterException::class)
infix fun HandlerContext.getQueryParameter(name: String): String = call.request
    .queryParameters[name]
    ?: throw MissingRequestParameterException(name)

@Throws(BadRequestException::class)
inline fun <T : Any> HandlerContext.getQueryParameterAs(
    name: String,
    map: String.() -> T?
): T = getQueryParameter(name)
    .map()
    ?: throw BadRequestException("Request parameter $name is invalid.")

inline infix fun HandlerContext.measure(handler: HandlerContext.() -> Unit) {
    val httpUri: String = call.request.run { "${httpMethod.value} $uri" }
    application.log.info("Processing $httpUri...")
    val time: Long = measureTimeMillis { handler() }
    application.log.info("$httpUri completed in $time ms.")
}

@Throws(MissingRequestParameterException::class)
private infix fun HandlerContext.getPathParameter(name: String): String =
    call.parameters[name] ?: throw MissingRequestParameterException(name)
