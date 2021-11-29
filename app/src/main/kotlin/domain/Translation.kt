package domain

import arrow.core.Option
import arrow.core.computations.option
import arrow.core.getOrNone
import kotlinx.serialization.Serializable

suspend fun Map<String, String>.toTranslation(): Option<Translation> = option {
    val french: String = getOrNone(Translation::french.name)
        .bind()
    val fang: String = getOrNone(Translation::fang.name)
        .bind()
    Translation(french, fang)
}

@Serializable
data class Translation(val french: String, val fang: String)
