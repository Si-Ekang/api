package core.models

import core.types.strictlyPositive
import core.types.toPaginationSize
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

class PaginationTest {
    companion object {
        private const val VALID_PAGE: Int = 1
        private const val VALID_SIZE: Int = 10
    }

    @Nested
    inner class Creation {
        @Test
        fun `should pass`() {
            val pagination1: Pagination = VALID_SIZE.toPaginationSize()!!
                .withPage(VALID_PAGE.strictlyPositive()!!)
            val pagination2: Pagination =
                pagination1.page withSize pagination1.size
            assertEquals(pagination1, pagination2)
        }
    }

    @Nested
    inner class ToIndexRange {
        @Test
        fun `should pass`() {
            val pagination: Pagination = VALID_SIZE.toPaginationSize()!!
                .withPage(VALID_PAGE.strictlyPositive()!!)
            val min: Int = (VALID_PAGE - 1) * VALID_SIZE
            val max: Int = VALID_PAGE * VALID_SIZE - 1
            assertEquals(pagination.toIndexRange(), actual = min..max)
        }
    }
}
