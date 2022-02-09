package config

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.installStatusPages() {
    install(StatusPages) {
        handleBadRequest()
        handleThrowable()
    }
}

private fun StatusPages.Configuration.handleBadRequest(): Unit =
    exception<BadRequestException> {
        call.respond(HttpStatusCode.BadRequest, it.localizedMessage)
    }

private fun StatusPages.Configuration.handleThrowable(): Unit =
    exception<Throwable> {
        call.respond(HttpStatusCode.InternalServerError)
        val message: String =
            if (it is IllegalStateException) it.localizedMessage
            else it.stackTraceToString()
        application.log.error(message)
    }
