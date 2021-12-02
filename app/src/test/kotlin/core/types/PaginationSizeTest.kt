package core.types

import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PaginationSizeTest {
    private companion object {
        private const val VALID_INT: Int = 10
    }

    @Nested
    inner class Creation {
        @Test
        fun `should pass`() {
            listOf(VALID_INT, 50)
                .map(Int::toPaginationSize)
                .forEach(::assertNotNull)
            listOf("10", "50")
                .map(String::toPaginationSize)
                .forEach(::assertNotNull)
        }

        @Test
        fun `should fail`() {
            listOf(9, 51)
                .map(Int::toPaginationSize)
                .forEach(::assertNull)
            listOf("9", "51", "a")
                .map(String::toPaginationSize)
                .forEach(::assertNull)
        }
    }

    @Nested
    inner class Times {
        @Test
        fun `should pass with Int`() {
            val x: PaginationSize = VALID_INT.toPaginationSize()!!
            val y = 2
            assertEquals(expected = x * y, actual = y * x)
        }

        @Test
        fun `should pass with StrictlyPositiveInt`() {
            val x: PaginationSize = VALID_INT.toPaginationSize()!!
            val y: StrictlyPositiveInt = 2.strictlyPositive()!!
            assertEquals(expected = x * y, actual = y * x)
        }
    }
}
