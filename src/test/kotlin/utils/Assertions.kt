package utils

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

infix fun <T : Any?> T.assertEquals(other: T): Unit = assertEquals(this, other)

fun <T : Any?> T.assertNotNull() {
    assertNotNull(this)
}

fun <T : Any?> T.assertNull(): Unit = assertNull(this)
