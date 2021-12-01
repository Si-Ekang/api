package core.types

@Suppress("DEPRECATION")
fun Int.toPaginationSize(): PaginationSize? = PaginationSize of this

@JvmInline
value class PaginationSize private constructor(val value: Int) {
    companion object {
        private const val MAX: Int = 50
        private const val MIN: Int = 10

        @Deprecated(message = "Use `Int.toPaginationSize` instead.")
        infix fun of(value: Int): PaginationSize? =
            value.takeIf { it in MIN..MAX }
                ?.let(::PaginationSize)
    }
}
