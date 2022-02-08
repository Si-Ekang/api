package translations.models

import utils.assertNotNull
import utils.assertNull
import kotlin.test.Test

class PaginationSizeTest {
    private val sizeRange: IntRange by lazy { PaginationSize.range }

    @Test
    fun `creation should pass`(): Unit = listOf(sizeRange.first, sizeRange.last)
        .run {
            map(Int::paginationSize) + map { it.toString().paginationSize }
        }.forEach(PaginationSize?::assertNotNull)

    @Test
    fun `creation should fail`(): Unit =
        listOf(sizeRange.first - 1, sizeRange.last + 1).run {
            map(Int::paginationSize) + "a".paginationSize + map {
                it.toString().paginationSize
            }
        }.forEach(PaginationSize?::assertNull)
}
