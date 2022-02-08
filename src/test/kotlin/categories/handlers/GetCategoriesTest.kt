package categories.handlers

import config.installContentNegotiation
import config.installStatusPages
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import utils.assertEquals
import utils.assertNotNull
import kotlin.test.Test

private fun Application.testingModule() {
    installContentNegotiation()
    installStatusPages()
    routing {
        get { getCategories() }
    }
}

class GetCategoriesTest {
    @Test
    fun `should return 200 OK`(): Unit =
        withTestApplication(Application::testingModule) {
            handleRequest(HttpMethod.Get, "").response.run {
                status() assertEquals HttpStatusCode.OK
                content.assertNotNull()
            }
        }
}
