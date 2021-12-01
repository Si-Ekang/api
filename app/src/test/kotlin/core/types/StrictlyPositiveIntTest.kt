package core.types

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class StrictlyPositiveIntTest {
    @Test
    fun `creation should pass`() {
        1.strictlyPositive()
            .let(::assertNotNull)
        "1".toStrictlyPositiveInt()
            .let(::assertNotNull)
    }

    @Test
    fun `creation should fail`() {
        0.strictlyPositive()
            .let(::assertNull)
        listOf("0", "a")
            .map(String::toStrictlyPositiveInt)
            .forEach(::assertNull)
    }
}
