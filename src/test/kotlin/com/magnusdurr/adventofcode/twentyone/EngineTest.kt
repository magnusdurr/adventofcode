package com.magnusdurr.adventofcode.twentyone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EngineTest {

    @Test
    fun `day three - engine diagnostics`() {
        val readings = listOf(
            "00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010"
        )

        val diagnostics = Submarine().engineDiagnostics(readings)
        assertThat(diagnostics.gamma).isEqualTo(22)
        assertThat(diagnostics.epsilon).isEqualTo(9)
        assertThat(diagnostics.powerConsumption()).isEqualTo(198)

        assertThat(diagnostics.oxygenRating).isEqualTo(23)
        assertThat(diagnostics.co2ScrubberRating).isEqualTo(10)
        assertThat(diagnostics.lifeSupportRating()).isEqualTo(230)
    }
}