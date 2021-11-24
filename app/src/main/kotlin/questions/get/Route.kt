package questions.get

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import java.io.File

fun Route.getRandomQuestions() {
    get(path = "questions") {
        // TODO: Not implemented yet
        application.environment.classLoader.getTranslationsFile()
            .fold({
                call.respond(
                    HttpStatusCode.InternalServerError,
                    message = "Translations file not found"
                )
            }) { file: File ->
                file.translations.buffer()
                    .collect { println(it) }
                call.respond(HttpStatusCode.NotImplemented)
            }
    }
}
