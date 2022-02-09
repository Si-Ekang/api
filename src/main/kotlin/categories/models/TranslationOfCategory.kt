package categories.models

import common.Csv
import io.github.kotools.csv.reader.csvReader

suspend infix fun Csv.getTranslationsOfCategory(id: Int):
        Set<TranslationOfCategory> = csvReader<TranslationOfCategory> {
    file = "categories-translations"
    folder = FOLDER
    filter { categoryId == id }
}.toSet()

data class TranslationOfCategory(val categoryId: Int, val translationId: Int)
