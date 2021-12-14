import com.magnusdurr.adventofcode.twentyone.Submarine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SubmarineTest {

    private val submarine = Submarine()

    @Test
    fun `day one - verify depth calculations`() {
        assertThat(
            submarine.calculateIncreasingDepth(
                listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
            )
        ).isEqualTo(7)
    }
}