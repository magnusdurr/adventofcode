package com.magnusdurr.adventofcode.twentyone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LavaTubeScanTest {

    val input = listOf("2199943210", "3987894921", "9856789892", "8767896789", "9899965678")

    @Test
    fun `day nine - verify test data`() {
        val lavaTubeScan = LavaTubeScan.parse(input)
        val lowPoints = lavaTubeScan.findLowPoints()

        assertThat(lowPoints).hasSize(4)
        assertThat(lowPoints.sumOf { it.riskLevel }).isEqualTo(15)
    }

    @Test
    fun `day nine - verify basin size`() {
        val lavaTubeScan = LavaTubeScan.parse(input)
        val lowPoints = lavaTubeScan.findLowPoints()

        assertThat(lavaTubeScan.multipliedSizeOfThreeLargestBasins(lowPoints)).isEqualTo(1134)
    }
}