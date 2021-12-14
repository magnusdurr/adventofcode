package com.magnusdurr.adventofcode.twentyone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SubmarineTest {

    @Test
    fun `day two - verify steering`() {
        val submarine = Submarine()
        submarine.drive("forward", 5)
        submarine.drive("down", 5)
        submarine.drive("forward", 8)
        submarine.drive("up", 3)
        submarine.drive("down", 8)
        submarine.drive("forward", 2)

        assertThat(submarine.horizontal).isEqualTo(15)
        assertThat(submarine.depth).isEqualTo(60)
        assertThat(submarine.position()).isEqualTo(900)
    }
}