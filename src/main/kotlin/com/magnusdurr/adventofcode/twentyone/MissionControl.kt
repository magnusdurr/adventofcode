package com.magnusdurr.adventofcode.twentyone

import java.io.File

class MissionControl {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val missionControl = MissionControl()

            val dayOneScans = File("src/main/resources/day-one-sonar.txt").readLines().map { it.toInt() }
            println("day one sonar scan - ${missionControl.dayOneSonarScan(dayOneScans)}")
            println("day one sonar scan with sliding window - ${missionControl.dayOneSonarWithSlidingWindows(dayOneScans)}")

            val submarine = Submarine()

            val dayTwoDrive = File("src/main/resources/day-two-drive.txt").readLines().map {
                val tokens = it.split(" ")
                tokens.first() to tokens.last().toInt()
            }
            dayTwoDrive.forEach { submarine.drive(it.first, it.second) }
            println("day two submarine position - ${submarine.position()}")

            val dayThreeDiagnostics = File("src/main/resources/day-three-diagnostics.txt").readLines()
            val diagnostics = submarine.engineDiagnostics(dayThreeDiagnostics)
            println("day three power consumption - ${diagnostics.powerConsumption()}")
            println("day three life support rating - ${diagnostics.lifeSupportRating()}")
        }
    }

    fun dayOneSonarScan(scans: List<Int>): Int = DepthScan(scans).calculateIncreasingDepth()
    fun dayOneSonarWithSlidingWindows(scans: List<Int>): Int =
        DepthScan(scans).toSlidingWindows().calculateIncreasingDepth()
}