@file:Suppress("DEPRECATION")

package core.types

fun Int.strictlyPositive(): StrictlyPositiveInt? = StrictlyPositiveInt of this

fun String.toStrictlyPositiveInt(): StrictlyPositiveInt? = toIntOrNull()
    ?.let { StrictlyPositiveInt of it }

@JvmInline
value class StrictlyPositiveInt private constructor(val value: Int) {
    companion object {
        private const val MIN: Int = 0

        @Deprecated(message = "Use `Int.strictlyPositive` instead.")
        infix fun of(value: Int): StrictlyPositiveInt? =
            value.takeIf { it > MIN }
                ?.let(::StrictlyPositiveInt)
    }
}
