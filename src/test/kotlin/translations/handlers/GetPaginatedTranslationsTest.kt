package translations.handlers

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
import translations.models.PaginationSize
import translations.models.StrictlyPositiveInt

private fun <R> testApplication(test: TestApplicationEngine.() -> R) {
    val testingModule: Application.() -> Unit = {
        installContentNegotiation()
        installStatusPages()
        routing {
            get { getPaginatedTranslations() }
        }
    }
    withTestApplication(testingModule, test)
}

private fun TestApplicationEngine.callWith(
    page: Int,
    size: Int
): TestApplicationResponse =
    handleRequest(HttpMethod.Get, "?page=$page&size=$size").response

class GetPaginatedTranslationsTest {
    private val sizeRange: IntRange = PaginationSize.range

    @Test
    fun `should return 200 OK`(): Unit = testApplication {
        callWith(StrictlyPositiveInt.MIN, sizeRange.random()).run {
            status() assertEquals HttpStatusCode.OK
            content.assertNotNull()
        }
    }

    @Test
    fun `should return 204 No Content`(): Unit = testApplication {
        callWith(page = 1000, sizeRange.last).run {
            status() assertEquals HttpStatusCode.NoContent
            content.assertNull()
        }
    }

    @Test
    fun `should return 400 Bad Request with invalid page`(): Unit =
        testApplication {
            callWith(StrictlyPositiveInt.MIN - 1, sizeRange.random()).run {
                status() assertEquals HttpStatusCode.BadRequest
                content.assertNotNull()
            }
        }

    @Test
    fun `should return 400 Bad Request with invalid size`(): Unit =
        testApplication {
            listOf(sizeRange.first - 1, sizeRange.last + 1)
                .map { callWith(StrictlyPositiveInt.MIN, it) }
                .forEach {
                    it.status() assertEquals HttpStatusCode.BadRequest
                    it.content.assertNotNull()
                }
        }
}
