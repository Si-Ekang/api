package core.types

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class StrictlyPositiveIntTest {
    @Test
    fun `creation should pass`() {
        1.strictlyPositive()
            .let(::assertNotNull)
    }

    @Test
    fun `creation should fail`(): Unit = 0.strictlyPositive()
        .let(::assertNull)
}
