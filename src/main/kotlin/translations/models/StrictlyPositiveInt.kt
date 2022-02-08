package translations.models

fun Int.strictlyPositive(): StrictlyPositiveInt? = StrictlyPositiveInt of this

fun String.toStrictlyPositiveInt(): StrictlyPositiveInt? = toIntOrNull()
    ?.strictlyPositive()

@JvmInline
value class StrictlyPositiveInt private constructor(val value: Int) {
    companion object {
        const val MIN: Int = 1

        infix fun of(value: Int): StrictlyPositiveInt? = value
            .takeIf { it >= MIN }
            ?.let(::StrictlyPositiveInt)
    }
}
