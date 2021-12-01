package core.models

import core.types.PaginationSize
import core.types.StrictlyPositiveInt

infix fun PaginationSize.withPage(page: StrictlyPositiveInt): Pagination =
    Pagination(page, size = this)

infix fun StrictlyPositiveInt.withSize(size: PaginationSize): Pagination =
    Pagination(page = this, size)

data class Pagination(val page: StrictlyPositiveInt, val size: PaginationSize)
