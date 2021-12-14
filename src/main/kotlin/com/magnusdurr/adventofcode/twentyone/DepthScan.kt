package com.magnusdurr.adventofcode.twentyone

data class DepthScan(val readings: List<Int>) {

    fun calculateIncreasingDepth(): Int {
        var last: Int? = null

        return readings.count {
            val result = last != null && last!! < it
            last = it
            result
        }
    }

    fun toSlidingWindows(): DepthScan {
        val sliding = readings.subList(0, 2).toMutableList()

        return DepthScan(readings
            .drop(2)
            .map {
                sliding.add(it)
                if (sliding.size > 3) {
                    sliding.removeFirst()
                }
                sliding.sum()
            })
    }
}