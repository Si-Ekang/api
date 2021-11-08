import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AppTest {
    @Test
    fun `should pass`(): Unit = withTestApplication({ main(testing = true) }) {
        handleRequest(HttpMethod.Get, uri = "/").apply {
            assertEquals(HttpStatusCode.OK, response.status())
            assertNotNull(response.content)
        }
    }
}
