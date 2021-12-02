@file:Suppress("DEPRECATION")

package core.types

infix operator fun Int.minus(other: StrictlyPositiveInt): Int =
    this - other.value

infix operator fun Int.times(other: StrictlyPositiveInt): Int =
    this * other.value

fun Int.strictlyPositive(): StrictlyPositiveInt? = StrictlyPositiveInt of this

fun String.toStrictlyPositiveInt(): StrictlyPositiveInt? = toIntOrNull()
    ?.strictlyPositive()

@JvmInline
value class StrictlyPositiveInt private constructor(val value: Int) {
    companion object {
        const val MIN: Int = 1

        @Deprecated(message = "Use `Int.strictlyPositive` instead.")
        infix fun of(value: Int): StrictlyPositiveInt? =
            value.takeIf { it >= MIN }
                ?.let(::StrictlyPositiveInt)
    }

    infix operator fun minus(other: Int): Int = value - other

    infix operator fun times(other: Int): Int = value * other

    infix operator fun times(other: PaginationSize): Int = value * other

    infix operator fun times(other: StrictlyPositiveInt): Int =
        value * other.value
}
