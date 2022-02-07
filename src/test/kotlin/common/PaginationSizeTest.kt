package common

import org.junit.jupiter.api.Nested
import x.assertNotNull
import x.assertNull
import kotlin.test.Test

class PaginationSizeTest {
    private val sizeRange: IntRange by lazy { PaginationSize.range }

    @Nested
    inner class Creation {
        @Test
        fun `should pass`() {
            val x: Int = sizeRange.first
            val y: Int = sizeRange.last
            listOf(x, y)
                .map(Int::toPaginationSize)
                .forEach(PaginationSize?::assertNotNull)
            listOf(x.toString(), y.toString())
                .map(String::toPaginationSize)
                .forEach(PaginationSize?::assertNotNull)
        }

        @Test
        fun `should fail`() {
            val x: Int = sizeRange.first - 1
            val y: Int = sizeRange.last + 1
            listOf(x, y)
                .map(Int::toPaginationSize)
                .forEach(PaginationSize?::assertNull)
            listOf(x.toString(), y.toString(), "a")
                .map(String::toPaginationSize)
                .forEach(PaginationSize?::assertNull)
        }
    }
}
