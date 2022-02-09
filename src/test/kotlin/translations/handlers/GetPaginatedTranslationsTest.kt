package translations.handlers

import config.installContentNegotiation
import config.installStatusPages
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import translations.models.PaginationSize
import translations.models.StrictlyPositiveInt
import utils.assertEquals
import utils.assertNotNull
import utils.assertNull
import kotlin.test.Test

private fun Application.testingModule() {
    installContentNegotiation()
    installStatusPages()
    routing {
        get { getPaginatedTranslations() }
    }
}

private fun TestApplicationEngine.callWith(
    page: Int,
    size: Int
): TestApplicationResponse =
    handleRequest(HttpMethod.Get, "?page=$page&size=$size").response

class GetPaginatedTranslationsTest {
    private val sizeRange: IntRange = PaginationSize.range

    @Test
    fun `should return 200 OK`(): Unit =
        withTestApplication(Application::testingModule) {
            callWith(StrictlyPositiveInt.MIN, sizeRange.random()).run {
                status() assertEquals HttpStatusCode.OK
                content.assertNotNull()
            }
        }

    @Test
    fun `should return 204 No Content`(): Unit =
        withTestApplication(Application::testingModule) {
            callWith(page = 1000, sizeRange.last).run {
                status() assertEquals HttpStatusCode.NoContent
                content.assertNull()
            }
        }

    @Test
    fun `should return 400 Bad Request with invalid page`(): Unit =
        withTestApplication(Application::testingModule) {
            callWith(StrictlyPositiveInt.MIN - 1, sizeRange.random()).run {
                status() assertEquals HttpStatusCode.BadRequest
                content.assertNotNull()
            }
        }

    @Test
    fun `should return 400 Bad Request with invalid size`(): Unit =
        withTestApplication(Application::testingModule) {
            listOf(sizeRange.first - 1, sizeRange.last + 1)
                .map { callWith(StrictlyPositiveInt.MIN, it) }
                .forEach {
                    it.status() assertEquals HttpStatusCode.BadRequest
                    it.content.assertNotNull()
                }
        }
}
