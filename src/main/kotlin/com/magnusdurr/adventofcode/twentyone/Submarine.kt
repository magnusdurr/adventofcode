package com.magnusdurr.adventofcode.twentyone

class Submarine {

    fun calculateIncreasingDepth(depth: List<Int>): Int {
        var last: Int? = null

        return depth.count {
            val result = last != null && last!! < it
            last = it
            result
        }
    }
}