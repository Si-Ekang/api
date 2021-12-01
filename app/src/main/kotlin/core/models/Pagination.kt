@file:Suppress("DEPRECATION")

package core.models

import core.types.PaginationSize
import core.types.StrictlyPositiveInt
import core.types.times

infix fun <T> List<T>.subList(pagination: Pagination): List<T> {
    val range: IntRange = pagination.toIndexRange()
    return if (range.first >= size) emptyList()
    else if (range.last >= size) subList(range.first, size)
    else subList(range.first, range.last + 1)
}

infix fun PaginationSize.withPage(page: StrictlyPositiveInt): Pagination =
    Pagination(page, size = this)

infix fun StrictlyPositiveInt.withSize(size: PaginationSize): Pagination =
    Pagination(page = this, size)

data class Pagination
@Deprecated(
    message = "Use `PaginationSize.withPage` or " +
            "`StrictlyPositiveInt.withSize` instead."
)
constructor(val page: StrictlyPositiveInt, val size: PaginationSize) {
    fun toIndexRange(): IntRange {
        val min: Int = (page - 1) * size
        val max: Int = page * size - 1
        return min..max
    }
}
