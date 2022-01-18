package exposition.routes

import io.ktor.http.content.*
import io.ktor.routing.*

fun Routing.serveDocs() {
    static {
        resources(resourcePackage = "openapi")
        resource(remotePath = "/", resource = "openapi/index.html")
    }
}
