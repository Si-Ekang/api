@file:Suppress("DEPRECATION")

package core.types

import common.StrictlyPositiveInt
import common.strictlyPositive
import common.toStrictlyPositiveInt

fun Int.toPaginationSize(): PaginationSize? = strictlyPositive()
    ?.let { PaginationSize of it }

fun String.toPaginationSize(): PaginationSize? = toStrictlyPositiveInt()
    ?.let { PaginationSize of it }

@JvmInline
value class PaginationSize private constructor(
    private val _value: StrictlyPositiveInt
) {
    val value: Int get() = _value.value

    companion object {
        val range: IntRange = 10..50

        @Deprecated(message = "Use `Int.toPaginationSize` instead.")
        infix fun of(value: StrictlyPositiveInt): PaginationSize? =
            value.takeIf { it.value in range }
                ?.let(::PaginationSize)
    }
}
