package core.types

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PaginationSizeTest {
    @Test
    fun `creation should pass`() {
        listOf(10, 50)
            .map(Int::toPaginationSize)
            .forEach(::assertNotNull)
        listOf("10", "50")
            .map(String::toPaginationSize)
            .forEach(::assertNotNull)
    }

    @Test
    fun `creation should fail`() {
        listOf(9, 51)
            .map(Int::toPaginationSize)
            .forEach(::assertNull)
        listOf("9", "51", "a")
            .map(String::toPaginationSize)
            .forEach(::assertNull)
    }
}
