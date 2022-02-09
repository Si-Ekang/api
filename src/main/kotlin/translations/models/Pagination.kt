package translations.models

import common.HandlerContext
import common.getQueryParameterAs

val HandlerContext.pagination: Pagination
    get() = Pagination(
        getQueryParameterAs("page") { strictlyPositiveInt },
        getQueryParameterAs("size") { paginationSize }
    )

data class Pagination(val page: StrictlyPositiveInt, val size: PaginationSize)
