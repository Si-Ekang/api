package categories.handlers

import common.idParameter
import config.installContentNegotiation
import config.installStatusPages
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import kotools.assert.Test
import kotools.assert.assertEquals
import kotools.assert.assertNotNull
import kotools.assert.assertNull

private fun <R> testApplication(test: TestApplicationEngine.() -> R) {
    val testingModule: Application.() -> Unit = {
        installContentNegotiation()
        installStatusPages()
        routing {
            get("{$idParameter}") { getTranslationsOfCategory() }
        }
    }
    withTestApplication(testingModule, test)
}

private infix fun TestApplicationEngine.callWithId(
    id: Int
): TestApplicationResponse = handleRequest(HttpMethod.Get, "$id").response

class GetTranslationsOfCategoryTest {
    @Test
    fun `should return 200 OK`(): Unit = testApplication {
        callWithId(1).run {
            status() assertEquals HttpStatusCode.OK
            content.assertNotNull()
        }
    }

    @Test
    fun `should return 400 Bad Request`(): Unit = testApplication {
        callWithId(0).run {
            status() assertEquals HttpStatusCode.BadRequest
            content.assertNotNull()
        }
    }

    @Test
    fun `should return 404 Not Found`(): Unit = testApplication {
        callWithId(Int.MAX_VALUE).run {
            status() assertEquals HttpStatusCode.NotFound
            content.assertNull()
        }
    }
}
