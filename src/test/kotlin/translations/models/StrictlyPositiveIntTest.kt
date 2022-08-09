package translations.models

import kotools.assert.Test
import kotools.assert.assertNotNull
import kotools.assert.assertNull

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
