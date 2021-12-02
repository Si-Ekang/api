package exposition.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

private fun Application.testingModule() {
    routing(Routing::serveDocs)
}

class ServeDocsTest {
    @Test
    fun `should return 200 OK`(): Unit =
        withTestApplication(Application::testingModule) {
            val response: TestApplicationResponse =
                handleRequest(HttpMethod.Get, uri = "/").response
            assertEquals(HttpStatusCode.OK, response.status())
            assertNotNull(response.content)
        }
}
