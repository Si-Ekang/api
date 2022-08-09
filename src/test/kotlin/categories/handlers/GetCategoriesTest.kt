package categories.handlers

import config.installContentNegotiation
import config.installStatusPages
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import kotools.assert.Test
import kotools.assert.assertEquals
import kotools.assert.assertNotNull

private fun <R> testApplication(test: TestApplicationEngine.() -> R) {
    val testingModule: Application.() -> Unit = {
        installContentNegotiation()
        installStatusPages()
        routing {
            get { getCategories() }
        }
    }
    withTestApplication(testingModule, test)
}

class GetCategoriesTest {
    @Test
    fun `should return 200 OK`(): Unit = testApplication {
        handleRequest(HttpMethod.Get, "").response.run {
            status() assertEquals HttpStatusCode.OK
            content.assertNotNull()
        }
    }
}
