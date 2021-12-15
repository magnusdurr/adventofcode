package com.magnusdurr.adventofcode.twentyone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FishSchoolTest {

    val testData = listOf(3, 4, 3, 1, 2)

    @Test
    fun `day six - fish spawning`() {
        val fishSchool = FishSchool(testData)

        fishSchool.simulateDays(18)
        assertThat(fishSchool.size()).isEqualTo(26)

        fishSchool.simulateDays(62)
        assertThat(fishSchool.size()).isEqualTo(5934)
    }

    @Test
    fun `day six - fish spawning low mem`() {
        val fishSchool = FishSchool(testData)

        fishSchool.simulateDaysLowMem(18)
        assertThat(fishSchool.lowMemSize()).isEqualTo(26)

        fishSchool.simulateDaysLowMem(62)
        assertThat(fishSchool.lowMemSize()).isEqualTo(5934)
    }
}