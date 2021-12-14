package com.magnusdurr.adventofcode.twentyone

import java.io.File

class MissionControl {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val missionControl = MissionControl()

            println("day one sonar scan - ${missionControl.dayOneSonarScan()}")
        }
    }

    private val submarine = Submarine()

    fun dayOneSonarScan(): Int = submarine.calculateIncreasingDepth(
        File("src/main/resources/day-one-sonar.txt").readLines().map { it.toInt() }
    )
}