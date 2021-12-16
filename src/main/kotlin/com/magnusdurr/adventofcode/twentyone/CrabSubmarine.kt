package com.magnusdurr.adventofcode.twentyone

import kotlin.math.abs

class CrabSubmarine(val position: Int) {

    companion object {
        fun slowAndSimple(initialPositions: List<Int>): Pair<Int, Int> {
            val crabSubmarines = initialPositions.map { CrabSubmarine(it) }

            return (initialPositions.minOrNull()!! until initialPositions.maxOrNull()!!)
                .map { it to crabSubmarines.sumOf { sub -> sub.distanceTo(it) } }
                .sortedBy { it.second }
                .first()
        }
    }

    fun distanceTo(targetLocation: Int): Int {
        return abs(position - targetLocation)
    }
}