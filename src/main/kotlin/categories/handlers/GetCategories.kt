package categories.handlers

import categories.models.Category
import categories.models.getCategories
import common.Csv
import common.HandlerContext
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend fun HandlerContext.getCategories(): Unit = Csv.getCategories()
    .takeIf(Set<Category>::isNotEmpty)
    ?.let { call.respond(HttpStatusCode.OK, it) }
    ?: error("No category available!")
