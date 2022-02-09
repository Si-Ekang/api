package translations.models

val Int.paginationSize: PaginationSize?
    get() = strictlyPositive?.let { PaginationSize of it }

val String.paginationSize: PaginationSize?
    get() = strictlyPositiveInt?.let { PaginationSize of it }

@JvmInline
value class PaginationSize private constructor(
    private val _value: StrictlyPositiveInt
) {
    val value: Int get() = _value.value

    companion object {
        val range: IntRange = 10..50

        infix fun of(value: StrictlyPositiveInt): PaginationSize? = value
            .takeIf { it.value in range }
            ?.let(::PaginationSize)
    }
}
