package infrastructure

import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

val csvReader: CsvReader by lazy {
    csvReader { skipEmptyLine = true }
}
