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
            println("day six fish - no. of fish in school after 80 days ${fishSchool.size()}")
            fishSchool.simulateDaysLowMem(256)
            println("day six fish - no. of fish in school after 256 days ${fishSchool.lowMemSize()}")

            val daySevenCrab = File("src/main/resources/day-seven-crabs.txt")
                .readLines().first().split(",").map { it.toInt() }
            val simpleResult = CrabSubmarine.slowAndSimpleWithSimpleFuelCalculation(daySevenCrab)
            println("day seven crab submarine - optimal position is ${simpleResult.first}, spending ${simpleResult.second} fuel")

            val betterResult = CrabSubmarine.slowAndSimpleWithAccurateFuelCalculation(daySevenCrab)
            println("day seven crab submarine - optimal position is ${betterResult.first}, spending ${betterResult.second} fuel")

            val dayEightDisplay = File("src/main/resources/day-eight-display.txt").readLines()
            println(
                "day eight displays - number of 1,4,7 or 8 is ${SevenSegmentDisplay.sumOfUniqueNumbers(dayEightDisplay)}"
            )
            println(
                "day eight displays - sum of all output numbers ${
                    SevenSegmentDisplay.sumOfOutputNumbers(
                        dayEightDisplay
                    )
                }"
            )

            val dayNineScan = File("src/main/resources/day-nine-lava-tube.txt").readLines()
            val lavaTubeScan = LavaTubeScan.parse(dayNineScan)
            val lowPoints = lavaTubeScan.findLowPoints()
            println("day nine lava tube - tube low point risk ${lowPoints.sumOf { it.riskLevel }}")
            println("day nine lava tube - basin sizes ${lavaTubeScan.multipliedSizeOfThreeLargestBasins(lowPoints)}")

            val dayTenLines = File("src/main/resources/day-ten-lines.txt").readLines()
            println(
                "day ten lines - syntax error score ${
                    NavigationProtocolParser.findBadLines(dayTenLines)
                        .let { NavigationProtocolParser.countErrorScore(it) }
                }"
            )

            println(
                "day ten lines - closing chars score ${
                    NavigationProtocolParser.findClosingSequences(dayTenLines)
                        .map { NavigationProtocolParser.countClosingScore(it) }.middle()
                }"
            )
        }
    }

    fun dayOneSonarScan(scans: List<Int>): Int = DepthScan(scans).calculateIncreasingDepth()
    fun dayOneSonarWithSlidingWindows(scans: List<Int>): Int =
        DepthScan(scans).toSlidingWindows().calculateIncreasingDepth()
}