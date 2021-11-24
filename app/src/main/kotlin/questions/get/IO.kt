package questions.get

import arrow.core.Option
import arrow.core.computations.option
import arrow.core.toOption
import com.github.doyaaaaaken.kotlincsv.client.CsvFileReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

val File.translations: Flow<Translation>
    get() = flow {
        openAsCsv { emitAll(lines.map(Map<String, String>::toTranslation)) }
    }.flowOn(Dispatchers.IO)

private val CsvFileReader.lines: Flow<Map<String, String>>
    get() = flow {
        option {
            val header: List<String> = readNext()
                .toOption()
                .bind()
            while (true) {
                val translation: MutableMap<String, String> = mutableMapOf()
                readNext()
                    .toOption()
                    .bind()
                    .forEachIndexed { index: Int, word: String ->
                        translation += header[index] to word
                    }
                emit(translation)
            }
        }
    }

suspend fun ClassLoader.getTranslationsFile(): Option<File> = io {
    getResource("translations.csv")
        .toOption()
        .map(URL::getPath)
        .map(::File)
}

private suspend fun <T> io(block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO, block)

private suspend infix fun <T> File.openAsCsv(
    reader: suspend CsvFileReader.() -> T
): T = csvReader()
    .openAsync(file = this@openAsCsv, reader)
