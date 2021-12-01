@file:Suppress("DEPRECATION")

package core.models

import core.types.PaginationSize
import core.types.StrictlyPositiveInt

infix fun PaginationSize.withPage(page: StrictlyPositiveInt): Pagination =
    Pagination(page, size = this)

infix fun StrictlyPositiveInt.withSize(size: PaginationSize): Pagination =
    Pagination(page = this, size)

data class Pagination
@Deprecated(
    message = "Use `PaginationSize.withPage` or " +
            "`StrictlyPositiveInt.withSize` instead."
)
constructor(val page: StrictlyPositiveInt, val size: PaginationSize)
