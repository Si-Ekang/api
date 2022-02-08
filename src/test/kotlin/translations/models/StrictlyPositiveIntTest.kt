package translations.models

import utils.assertNotNull
import utils.assertNull
import kotlin.test.Test

class StrictlyPositiveIntTest {
    @Test
    fun `creation should pass`() {
        val x: Int = StrictlyPositiveInt.MIN
        x.strictlyPositive()
            .assertNotNull()
        x.toString()
            .toStrictlyPositiveInt()
            .assertNotNull()
    }

    @Test
    fun `creation should fail`() {
        val x: Int = StrictlyPositiveInt.MIN - 1
        x.strictlyPositive()
            .assertNull()
        listOf(x.toString(), "a")
            .map(String::toStrictlyPositiveInt)
            .forEach(StrictlyPositiveInt?::assertNull)
    }
}
