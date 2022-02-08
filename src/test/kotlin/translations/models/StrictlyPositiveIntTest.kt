package translations.models

import utils.assertNotNull
import utils.assertNull
import kotlin.test.Test

class StrictlyPositiveIntTest {
    private val Int.strictlyPositives: List<StrictlyPositiveInt?>
        get() = listOf(strictlyPositive, toString().strictlyPositiveInt)

    @Test
    fun `creation should pass`() = StrictlyPositiveInt.MIN.strictlyPositives
        .forEach(StrictlyPositiveInt?::assertNotNull)

    @Test
    fun `creation should fail`() = (StrictlyPositiveInt.MIN - 1).run {
        strictlyPositives + "a".strictlyPositiveInt
    }.forEach(StrictlyPositiveInt?::assertNull)
}
