package questions.get

typealias Translation = Map<Language, String>

fun Map<String, String>.toTranslation(): Translation = mapKeys {
    val key: String = it.key.uppercase()
    Language.valueOf(key)
}

data class Question(
    val answer: String,
    val language: Language,
    val suggestions: Set<String>,
    val word: String
)

enum class Language {
    FANG, FRENCH;

    override fun toString(): String = name.lowercase()
}
