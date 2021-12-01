package core.types

@Suppress("DEPRECATION")
fun Int.strictlyPositive(): StrictlyPositiveInt? = StrictlyPositiveInt of this

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
