package categories.models

import common.Csv
import io.github.kotools.csv.reader.csvReader
import kotools.types.number.StrictlyPositiveInt

suspend infix fun Csv.getTranslationsOfCategory(
    id: StrictlyPositiveInt
): Set<TranslationOfCategory> = csvReader<TranslationOfCategory> {
    file = "categories-translations"
    folder = FOLDER
    filter { categoryId == id.value }
}.toSet()

data class TranslationOfCategory(val categoryId: Int, val translationId: Int)
