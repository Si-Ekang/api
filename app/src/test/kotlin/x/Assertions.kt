package x

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

infix fun <T> T.assertEquals(other: T): Unit =
    assertEquals(expected = this, other)

fun <T> T?.assertNotNull() {
    assertNotNull(actual = this)
}

fun <T> T?.assertNull(): Unit = assertNull(actual = this)
