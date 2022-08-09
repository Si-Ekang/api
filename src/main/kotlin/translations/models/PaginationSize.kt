package translations.models

import kotools.types.number.StrictlyPositiveInt
import kotools.types.number.toStrictlyPositiveIntOrNull

val Int.paginationSize: PaginationSize?
    get() = toStrictlyPositiveIntOrNull()?.let(PaginationSize.Companion::of)

val String.paginationSize: PaginationSize?
    get() = toIntOrNull()
        ?.toStrictlyPositiveIntOrNull()
        ?.let(PaginationSize.Companion::of)

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
