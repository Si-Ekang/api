package translations.models

import io.github.kotools.csv.reader.csvReader
import kotlinx.serialization.Serializable

suspend fun readTranslationsFile(pagination: Pagination): Set<Translation> =
    csvReader<Translation> {
        file = "translations"
        pagination {
            page = pagination.page.value
            size = pagination.size.value
        }
    }.toSet()

@Serializable
data class Translation(val id: Int, val french: String, val fang: String) {
    override fun equals(other: Any?): Boolean =
        other is Translation && id == other.id

    override fun hashCode(): Int = 31 * id
}
