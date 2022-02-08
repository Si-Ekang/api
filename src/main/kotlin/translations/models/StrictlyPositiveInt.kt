package translations.models

val Int.strictlyPositive: StrictlyPositiveInt?
    get() = StrictlyPositiveInt of this

val String.strictlyPositiveInt: StrictlyPositiveInt?
    get() = toIntOrNull()?.strictlyPositive

@JvmInline
value class StrictlyPositiveInt private constructor(val value: Int) {
    companion object {
        const val MIN: Int = 1

        infix fun of(value: Int): StrictlyPositiveInt? = value
            .takeIf { it >= MIN }
            ?.let(::StrictlyPositiveInt)
    }
}
