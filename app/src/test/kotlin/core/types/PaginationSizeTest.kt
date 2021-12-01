package core.types

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PaginationSizeTest {
    @Test
    fun `creation should pass`(): Unit = listOf(10, 50)
        .map(Int::toPaginationSize)
        .forEach(::assertNotNull)

    @Test
    fun `creation should fail`(): Unit = listOf(9, 51)
        .map(Int::toPaginationSize)
        .forEach(::assertNull)
}
