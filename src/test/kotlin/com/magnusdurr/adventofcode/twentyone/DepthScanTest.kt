package com.magnusdurr.adventofcode.twentyone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DepthScanTest {

    private lateinit var depthScan: DepthScan

    @BeforeEach
    fun setup() {
        depthScan = DepthScan(listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263))
    }

    @Test
    fun `day one - verify depth calculations`() {
        assertThat(depthScan.calculateIncreasingDepth()).isEqualTo(7)
    }

    @Test
    fun `day one - create sliding windows`() {
        val slidingWindows: DepthScan = depthScan.toSlidingWindows()
        assertThat(slidingWindows.readings).hasSize(8).containsSequence(listOf(607, 618, 618, 617, 647, 716, 769, 792))
        assertThat(slidingWindows.calculateIncreasingDepth()).isEqualTo(5)
    }
}
