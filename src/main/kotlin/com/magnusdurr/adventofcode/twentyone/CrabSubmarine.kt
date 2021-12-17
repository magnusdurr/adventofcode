package com.magnusdurr.adventofcode.twentyone

import kotlin.math.abs
import kotlin.math.roundToInt

class CrabSubmarine(private val position: Int) {

    companion object {
        fun slowAndSimpleWithSimpleFuelCalculation(initialPositions: List<Int>): Pair<Int, Int> {
            val crabSubmarines = initialPositions.map { CrabSubmarine(it) }

            return (initialPositions.minOrNull()!! until initialPositions.maxOrNull()!!)
                .map { it to crabSubmarines.sumOf { sub -> sub.distanceTo(it) } }
                .minByOrNull { it.second }!!
        }

        fun slowAndSimpleWithAccurateFuelCalculation(initialPositions: List<Int>): Pair<Int, Int> {
            val crabSubmarines = initialPositions.map { CrabSubmarine(it) }

            return (initialPositions.minOrNull()!! until initialPositions.maxOrNull()!!)
                .map {
                    it to crabSubmarines.sumOf { sub ->
                        sub.distanceTo(it).let { distance -> sub.fuelConsumptionForDistance(distance) }
                    }
                }
                .minByOrNull { it.second }!!
        }
    }

    fun distanceTo(targetLocation: Int): Int = abs(position - targetLocation)
    fun fuelConsumptionForDistance(distance: Int): Int = ((distance + 1) * (distance.toDouble() / 2)).roundToInt()
}