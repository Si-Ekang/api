package infrastructure

import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.InputStream

private val csvReader: CsvReader by lazy {
    csvReader { skipEmptyLine = true }
}

infix fun CoroutineScope.getTranslationsFromFileAsync(loader: ClassLoader):
        Deferred<List<Map<String, String>>> = async {
    loader.getResourceAsStream("translations.csv")
        ?.getCsvLines()
        ?: error("Translations file not found.")
}

private suspend fun InputStream.getCsvLines(): List<Map<String, String>> =
    withContext(IO) { csvReader.readAllWithHeader(ips = this@getCsvLines) }
