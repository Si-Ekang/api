package translations

import common.PaginationSize
import common.StrictlyPositiveInt
import exposition.config.installContentNegotiation
import exposition.config.installStatusPages
import exposition.types.handle
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.routing.*
import io.ktor.server.testing.*
import x.assertEquals
import x.assertNotNull
import x.assertNull
import kotlin.test.Test

private const val PATH: String = "translations"

private val sizeRange: IntRange = PaginationSize.range

private fun Application.testingModule() {
    installContentNegotiation()
    installStatusPages()
    routing {
        get(PATH) {
            handle { getPaginatedTranslations() }
        }
    }
}

private fun TestApplicationEngine.callWith(
    page: Int,
    size: Int
): TestApplicationResponse =
    handleRequest(Get, uri = "$PATH?page=$page&size=$size").response

class GetPaginatedTranslationsTest {
    @Test
    fun `should return 200 OK`(): Unit =
        withTestApplication(Application::testingModule) {
            val page: Int = StrictlyPositiveInt.MIN
            val size: Int = sizeRange.random()
            val response: TestApplicationResponse = callWith(page, size)
            HttpStatusCode.OK assertEquals response.status()
            response.content.assertNotNull()
        }

    @Test
    fun `should return 204 No Content`(): Unit =
        withTestApplication(Application::testingModule) {
            val size: Int = sizeRange.last
            val response: TestApplicationResponse = callWith(page = 1000, size)
            HttpStatusCode.NoContent assertEquals response.status()
            response.content.assertNull()
        }

    @Test
    fun `should return 400 Bad Request with invalid page`(): Unit =
        withTestApplication(Application::testingModule) {
            val page: Int = StrictlyPositiveInt.MIN - 1
            val size: Int = sizeRange.random()
            val response: TestApplicationResponse = callWith(page, size)
            HttpStatusCode.BadRequest assertEquals response.status()
            response.assertNotNull()
        }

    @Test
    fun `should return 400 Bad Request with invalid size`(): Unit =
        withTestApplication(Application::testingModule) {
            listOf(sizeRange.first - 1, sizeRange.last + 1)
                .map { callWith(StrictlyPositiveInt.MIN, it) }
                .forEach {
                    HttpStatusCode.BadRequest assertEquals it.status()
                    it.content.assertNotNull()
                }
        }
}