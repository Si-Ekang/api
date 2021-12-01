package core.types

import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class StrictlyPositiveIntTest {
    private companion object {
        private const val VALID_INT: Int = 1
    }

    @Nested
    inner class Creation {
        @Test
        fun `should pass`() {
            VALID_INT.strictlyPositive()
                .let(::assertNotNull)
            "1".toStrictlyPositiveInt()
                .let(::assertNotNull)
        }

        @Test
        fun `should fail`() {
            0.strictlyPositive()
                .let(::assertNull)
            listOf("0", "a")
                .map(String::toStrictlyPositiveInt)
                .forEach(::assertNull)
        }
    }

    @Nested
    inner class Minus {
        @Test
        fun `should pass with Int`() {
            val x: StrictlyPositiveInt = VALID_INT.strictlyPositive()!!
            val y = 1
            assertEquals(actual = x - y, expected = y - x)
        }
    }

    @Nested
    inner class Times {
        @Test
        fun `should pass with Int`() {
            val x: StrictlyPositiveInt = VALID_INT.strictlyPositive()!!
            val y = 2
            assertEquals(actual = x * y, expected = y * x)
        }

        @Test
        fun `should pass with PaginationSize`() {
            val x: StrictlyPositiveInt = VALID_INT.strictlyPositive()!!
            val y: PaginationSize = 15.toPaginationSize()!!
            assertEquals(expected = x * y, actual = y * x)
        }

        @Test
        fun `should pass with StrictlyPositiveInt`() {
            val x: StrictlyPositiveInt = VALID_INT.strictlyPositive()!!
            val y: StrictlyPositiveInt = 5.strictlyPositive()!!
            assertEquals(expected = x * y, actual = y * x)
        }
    }
}
