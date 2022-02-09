package categories.handlers

import common.ID_PARAM
import config.installContentNegotiation
import config.installStatusPages
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import utils.assertEquals
import utils.assertNotNull
import utils.assertNull
import kotlin.test.Test

private fun Application.testingModule() {
    installContentNegotiation()
    installStatusPages()
    routing {
        get("{$ID_PARAM}") { getTranslationsOfCategory() }
    }
}

private infix fun TestApplicationEngine.callWith(id: Int):
        TestApplicationResponse =
    handleRequest(HttpMethod.Get, id.toString()).response

class GetTranslationsOfCategoryTest {
    @Test
    fun `should return 200 OK`(): Unit =
        withTestApplication(Application::testingModule) {
            callWith(1).run {
                status() assertEquals HttpStatusCode.OK
                content.assertNotNull()
            }
        }

    @Test
    fun `should return 400 Bad Request`(): Unit =
        withTestApplication(Application::testingModule) {
            callWith(0).run {
                status() assertEquals HttpStatusCode.BadRequest
                content.assertNotNull()
            }
        }

    @Test
    fun `should return 404 Not Found`(): Unit =
        withTestApplication(Application::testingModule) {
            callWith(Int.MAX_VALUE).run {
                status() assertEquals HttpStatusCode.NotFound
                content.assertNull()
            }
        }
}
