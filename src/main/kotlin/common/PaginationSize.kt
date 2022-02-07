package common

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

        infix fun of(value: StrictlyPositiveInt): PaginationSize? =
            value.takeIf { it.value in range }
                ?.let(::PaginationSize)
    }
}
