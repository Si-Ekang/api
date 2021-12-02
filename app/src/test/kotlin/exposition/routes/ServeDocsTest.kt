package exposition.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import x.assertEquals
import x.assertNotNull
import kotlin.test.Test

private fun Application.testingModule() {
    routing(Routing::serveDocs)
}

class ServeDocsTest {
    @Test
    fun `should return 200 OK`(): Unit =
        withTestApplication(Application::testingModule) {
            val response: TestApplicationResponse =
                handleRequest(HttpMethod.Get, uri = "/").response
            HttpStatusCode.OK assertEquals response.status()
            response.content.assertNotNull()
        }
}
