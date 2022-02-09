package categories.models

import common.Csv
import io.github.kotools.csv.reader.csvReader
import kotlinx.serialization.Serializable

suspend fun Csv.getCategories(): Set<Category> = csvReader<Category> {
    file = "categories"
    folder = FOLDER
}.toSet()

@Serializable
data class Category(val id: Int, val name: String) {
    override fun equals(other: Any?): Boolean =
        other is Category && id == other.id

    override fun hashCode(): Int = 31 * id
}
