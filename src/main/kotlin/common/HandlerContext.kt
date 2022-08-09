package common

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import kotools.types.number.StrictlyPositiveInt
import kotools.types.string.NotBlankString
import kotlin.system.measureTimeMillis

typealias HandlerContext = PipelineContext<Unit, ApplicationCall>

val idParameter: NotBlankString = NotBlankString("id")

@Suppress("unused")
suspend inline fun HandlerContext.devOnly(block: HandlerContext.() -> Unit):
        Unit = if (application.developmentMode) block()
else call.respond(HttpStatusCode.NotImplemented)

/**
 * Retrieves the id parameter from path, or throws a [BadRequestException] if
 * the id is not strictly positive.
 */
@Throws(BadRequestException::class)
fun HandlerContext.getIdFromPath(): StrictlyPositiveInt =
    getPathParameter(idParameter)
        .toIntOrNull()
        ?.let(StrictlyPositiveInt.Companion::orNull)
        ?: throw BadRequestException(
            "Request parameter $idParameter is invalid."
        )

@Throws(MissingRequestParameterException::class)
infix fun HandlerContext.getQueryParameter(name: String): String =
    call.request.queryParameters[name]
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
private infix fun HandlerContext.getPathParameter(
    name: NotBlankString
): String {
    val parameterName: String = name.value
    return call.parameters[parameterName]
        ?: throw MissingRequestParameterException(parameterName)
}
