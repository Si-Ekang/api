package core.models

import x.assertNotNull
import x.assertNull
import kotlin.test.Test

class TranslationTest {
    private companion object {
        private const val FANG_WORD: String = "ndüiny"
        private const val FRENCH_WORD: String = "aigle"
    }

    @Test
    fun `creation from Map of strings should pass`(): Unit = mapOf(
        Translation::french.name to FRENCH_WORD,
        Translation::fang.name to FANG_WORD
    ).toTranslation()
        .assertNotNull()

    @Test
    fun `creation from Map of strings should fail`(): Unit =
        mapOf("uh" to FRENCH_WORD, "oh" to FANG_WORD)
            .toTranslation()
            .assertNull()
}
