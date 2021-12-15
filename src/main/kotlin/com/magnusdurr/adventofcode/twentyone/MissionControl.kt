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

            val dayFourBingo = File("src/main/resources/day-four-bingo.txt").readLines()
            val bingoGame = BingoGame.loadFromFile(dayFourBingo)
            val (board, number) = bingoGame.findWinner()
            println("day four bingo - winner rating ${board.sumOfUnchecked() * number}")
            val (losingBoard, losingBoardNumber) = bingoGame.findLoser()
            println("day four bingo - loser rating ${losingBoard.sumOfUnchecked() * losingBoardNumber}")

            val dayFiveVents = File("src/main/resources/day-five-vents.txt").readLines()
            val hydrothermalVentScanner = HydrothermalVentScanner.loadInput(dayFiveVents)
            val simpleHeatMap = hydrothermalVentScanner.simpleHeatMap(2)
            println("day five vents - no. of hot spots in straight lines ${simpleHeatMap.size}")
            val heatMap = hydrothermalVentScanner.heatMap(2)
            println("day five vents - no. of hot spots ${heatMap.size}")

            val daySixFish =
                File("src/main/resources/day-six-fish.txt").readLines().first().split(",").map { it.toInt() }
            val fishSchool = FishSchool(daySixFish)
            fishSchool.simulateDays(80)
            println("day six fish - no. of fish in school ${fishSchool.size()}")
        }
    }

    fun dayOneSonarScan(scans: List<Int>): Int = DepthScan(scans).calculateIncreasingDepth()
    fun dayOneSonarWithSlidingWindows(scans: List<Int>): Int =
        DepthScan(scans).toSlidingWindows().calculateIncreasingDepth()
}