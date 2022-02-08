package translations.handlers

import common.HandlerContext
import common.getQueryParameterAs
import io.github.kotools.csv.reader.csvReader
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import kotlinx.serialization.Serializable
import translations.models.PaginationSize
import translations.models.StrictlyPositiveInt
import translations.models.paginationSize
import translations.models.toStrictlyPositiveInt

suspend fun HandlerContext.getPaginatedTranslations() {
    val pagination: Pagination = getQueryParametersAsPagination()
    val translations: Set<Translation> = csvReader<Translation> {
        file = "translations"
        pagination {
            page = pagination.page.value
            size = pagination.size.value
        }
    }.toSet()
    if (translations.isEmpty()) call.respond(HttpStatusCode.NoContent)
    else call.respond(HttpStatusCode.OK, translations)
}

private fun HandlerContext.getQueryParametersAsPagination(): Pagination {
    val page: StrictlyPositiveInt =
        getQueryParameterAs("page", String::toStrictlyPositiveInt)
    val size: PaginationSize = getQueryParameterAs("size") { paginationSize }
    return Pagination(page, size)
}

@Serializable
data class Translation(val id: Int, val french: String, val fang: String) {
    override fun equals(other: Any?): Boolean =
        other is Translation && id == other.id

    override fun hashCode(): Int = 31 * id
}

private data class Pagination(
    val page: StrictlyPositiveInt,
    val size: PaginationSize
)
