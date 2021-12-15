package com.magnusdurr.adventofcode.twentyone

import com.magnusdurr.adventofcode.twentyone.HydrothermalVentScanner.Point
import com.magnusdurr.adventofcode.twentyone.HydrothermalVentScanner.VentLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HydrothermalVentScannerTest {

    val testData = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )

    @Test
    fun `day five - calculate heat map`() {
        val ventScanner = HydrothermalVentScanner.loadInput(testData)

        val heatMap = ventScanner.simpleHeatMap(2)
        assertThat(heatMap).hasSize(5)
        assertThat(heatMap).contains(
            Point(3, 4), Point(7, 4), Point(0, 9), Point(1, 9), Point(2, 9)
        )
    }

    @Test
    fun `day five - calculate heat map with diagonals`() {
        val ventScanner = HydrothermalVentScanner.loadInput(testData)
        val heatMap = ventScanner.heatMap(2)
        assertThat(heatMap).hasSize(12)
    }

    @Test
    fun `check horizontal vent line`() {
        val ventLine = VentLine(Point(1, 3), Point(3, 3))
        assertThat(ventLine.isHorizontal()).isTrue
        assertThat(ventLine.isVertical()).isFalse
        assertThat(ventLine.points()).contains(Point(1, 3), Point(2, 3), Point(3, 3))
    }

    @Test
    fun `check horizontal backwards vent line`() {
        val ventLine = VentLine(Point(3, 3), Point(1, 3))
        assertThat(ventLine.isHorizontal()).isTrue
        assertThat(ventLine.isVertical()).isFalse
        assertThat(ventLine.points()).contains(Point(1, 3), Point(2, 3), Point(3, 3))
    }

    @Test
    fun `check vertical vent line`() {
        val ventLine = VentLine(Point(1, 1), Point(1, 3))
        assertThat(ventLine.isHorizontal()).isFalse
        assertThat(ventLine.isVertical()).isTrue
        assertThat(ventLine.points()).contains(Point(1, 1), Point(1, 2), Point(1, 3))
    }

    @Test
    fun `check vertical backwards vent line`() {
        val ventLine = VentLine(Point(1, 3), Point(1, 1))
        assertThat(ventLine.isHorizontal()).isFalse
        assertThat(ventLine.isVertical()).isTrue
        assertThat(ventLine.points()).contains(Point(1, 1), Point(1, 2), Point(1, 3))
    }
}