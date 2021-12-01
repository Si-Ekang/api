package core.models

import core.types.strictlyPositive
import core.types.toPaginationSize
import kotlin.test.Test
import kotlin.test.assertEquals

class PaginationTest {
    @Test
    fun `creation should pass`() {
        val pagination1: Pagination =
            10.toPaginationSize()!! withPage 1.strictlyPositive()!!
        val pagination2: Pagination = pagination1.page withSize pagination1.size
        assertEquals(pagination1, pagination2)
    }
}
