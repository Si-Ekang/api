package routing

import io.ktor.http.content.*
import io.ktor.routing.*

fun Routing.docs() {
    static {
        resources(resourcePackage = "openapi")
        resource(remotePath = "/", resource = "openapi/index.html")
    }
}
