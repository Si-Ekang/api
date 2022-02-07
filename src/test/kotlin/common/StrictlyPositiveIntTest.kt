package common

import org.junit.jupiter.api.Nested
import utils.assertNotNull
import utils.assertNull
import kotlin.test.Test

class StrictlyPositiveIntTest {
    @Nested
    inner class Creation {
        @Test
        fun `should pass`() {
            val x: Int = StrictlyPositiveInt.MIN
            x.strictlyPositive()
                .assertNotNull()
            x.toString()
                .toStrictlyPositiveInt()
                .assertNotNull()
        }

        @Test
        fun `should fail`() {
            val x: Int = StrictlyPositiveInt.MIN - 1
            x.strictlyPositive()
                .assertNull()
            listOf(x.toString(), "a")
                .map(String::toStrictlyPositiveInt)
                .forEach(StrictlyPositiveInt?::assertNull)
        }
    }
}
