package infrastructure

import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.File

private val csvReader: CsvReader by lazy {
    csvReader { skipEmptyLine = true }
}

infix fun CoroutineScope.getTranslationsFromFileAsync(loader: ClassLoader):
        Deferred<List<Map<String, String>>> = async {
    loader.getTranslationsFile()
        ?.getCsvLines()
        ?: error("Translations file not found.")
}

private fun ClassLoader.getTranslationsFile(): File? =
    getResource("translations.csv")
        ?.let { File(it.path) }

private suspend fun File.getCsvLines(): List<Map<String, String>> =
    withContext(IO) { csvReader.readAllWithHeader(file = this@getCsvLines) }
