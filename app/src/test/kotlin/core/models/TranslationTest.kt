package core.models

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TranslationTest {
    @Test
    fun `creation from Map of strings should pass`() {
        mapOf(
            Translation::french.name to "aigle",
            Translation::fang.name to "ndüiny"
        ).toTranslation()
            .let(::assertNotNull)
    }

    @Test
    fun `creation from Map of strings should fail`(): Unit =
        mapOf("uh" to "aigle", "oh" to "ndüiny")
            .toTranslation()
            .let(::assertNull)
}
