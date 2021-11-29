package domain

import arrow.core.Option
import arrow.core.toOption

infix fun Int.toIntAfter(limit: Int): Option<IntAfter> = takeIf { it > limit }
    .toOption()
    .map { IntAfter(it, limit) }

data class IntAfter(val value: Int, val limit: Int)
