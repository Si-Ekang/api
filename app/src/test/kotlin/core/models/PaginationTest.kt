package core.models

import core.types.*
import org.junit.jupiter.api.Nested
import x.assertEquals
import kotlin.test.Test

class PaginationTest {
    private companion object {
        private const val VALID_PAGE: Int = StrictlyPositiveInt.MIN
    }

    private val randomSize: Int get() = PaginationSize.range.random()

    @Nested
    inner class Creation {
        @Test
        fun `should pass`() {
            val size: PaginationSize = randomSize.toPaginationSize()!!
            val page: StrictlyPositiveInt = VALID_PAGE.strictlyPositive()!!
            (size withPage page) assertEquals (page withSize size)
        }
    }
}
