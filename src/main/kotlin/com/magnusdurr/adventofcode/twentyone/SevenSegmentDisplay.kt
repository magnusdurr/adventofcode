package com.magnusdurr.adventofcode.twentyone

class SevenSegmentDisplay(val input: List<String>, val output: List<String>) {

    companion object {
        fun sumOfUniqueNumbers(lines: List<String>): Int = lines.map {
            val inputOutput = it.split(" | ")
            SevenSegmentDisplay(
                inputOutput.first().split(" "),
                inputOutput.last().split(" ")
            )
        }.sumOf { it.numberOfUniqueNumbers() }
    }

    private fun numberOfUniqueNumbers(): Int = output.filter { it.length in listOf(2, 3, 4, 7) }.size
}