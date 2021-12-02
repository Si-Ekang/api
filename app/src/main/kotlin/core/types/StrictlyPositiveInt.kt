@file:Suppress("DEPRECATION")

package core.types

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
}
