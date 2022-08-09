package translations.models

import common.HandlerContext
import common.getQueryParameterAs
import kotools.types.number.StrictlyPositiveInt
import kotools.types.number.toStrictlyPositiveIntOrNull

val HandlerContext.pagination: Pagination
    get() = Pagination(
        getQueryParameterAs("page") {
            toIntOrNull()?.toStrictlyPositiveIntOrNull()
        },
        getQueryParameterAs("size") { paginationSize }
    )

data class Pagination(val page: StrictlyPositiveInt, val size: PaginationSize)
