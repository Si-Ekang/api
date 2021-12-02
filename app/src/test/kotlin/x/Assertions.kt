package x

import kotlin.test.assertEquals

infix fun <T> T.assertEquals(other: T): Unit =
    assertEquals(expected = this, other)
