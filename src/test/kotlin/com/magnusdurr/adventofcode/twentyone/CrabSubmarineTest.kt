package com.magnusdurr.adventofcode.twentyone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CrabSubmarineTest {

    val positions = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)

    @Test
    fun `day seven - verify simple but slow`() {
        val result = CrabSubmarine.slowAndSimpleWithSimpleFuelCalculation(positions)
        assertThat(result.first).isEqualTo(2)
        assertThat(result.second).isEqualTo(37)
    }

    @Test
    fun `day seven - verify simple but slow with good fuel calculation`() {
        val result = CrabSubmarine.slowAndSimpleWithAccurateFuelCalculation(positions)
        assertThat(result.first).isEqualTo(5)
        assertThat(result.second).isEqualTo(168)
    }
}