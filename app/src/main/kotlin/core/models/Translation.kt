package core.models

import kotlinx.serialization.Serializable

fun Map<String, String>.toTranslation(): Translation? {
    val french: String = get(Translation::french.name) ?: return null
    val fang: String = get(Translation::fang.name) ?: return null
    return Translation(french, fang)
}

@Serializable
data class Translation(val french: String, val fang: String)
