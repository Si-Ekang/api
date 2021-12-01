package routing

import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.NotImplemented
import io.ktor.response.*
import io.ktor.util.pipeline.*

suspend fun PipelineContext<Unit, ApplicationCall>.getPaginatedTranslations() {
    call.respond(NotImplemented)
}
