@file:Suppress("DEPRECATION")

package core.types

fun Int.toPaginationSize(): PaginationSize? = strictlyPositive()
    ?.let { PaginationSize of it }

fun String.toPaginationSize(): PaginationSize? = toStrictlyPositiveInt()
    ?.let { PaginationSize of it }

@JvmInline
value class PaginationSize private constructor(val value: StrictlyPositiveInt) {
    companion object {
        val range: IntRange = 10..50

        @Deprecated(message = "Use `Int.toPaginationSize` instead.")
        infix fun of(value: StrictlyPositiveInt): PaginationSize? =
            value.takeIf { it.value in range }
                ?.let(::PaginationSize)
    }
}
