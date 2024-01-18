package com.magnusdurr.adventofcode.twentythree

class Trebuchet {

    private val numbersAsText = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    internal fun numberFromLine(line: String): Int {
        val first = line.first { it.isDigit() }
        val last = line.last { it.isDigit() }

        return "$first$last".toInt()
    }

    internal fun numbersFromLinesWithText(line: String): Int {
        val first = firstTextDigitPosition(line).let {
            if (it != null) {
                line.replace(it.asText, it.asDigit)
            } else line
        }.first { it.isDigit() }

        val last = lastTextDigitPosition(line).let {
            if (it != null) {
                line.replace(it.asText, it.asDigit)
            } else line
        }.last { it.isDigit() }

        return "$first$last".toInt()
    }

    private fun firstTextDigitPosition(result: String) =
        numbersAsText.map { TextDigitPosition(it.key, it.value, result.indexOf(it.key)) }
            .filter { it.position != -1 }
            .minByOrNull { it.position }

    private fun lastTextDigitPosition(result: String) =
        numbersAsText.map { TextDigitPosition(it.key, it.value, result.lastIndexOf(it.key)) }
            .filter { it.position != -1 }
            .maxByOrNull { it.position }

    fun calibrationValueSum(lines: List<String>): Int = lines
        .map(::numberFromLine)
        .sum()

    fun calibrationValueSumIncludingText(lines: List<String>): Int = lines
        .map(::numbersFromLinesWithText)
        .sum()

    internal data class TextDigitPosition(
        val asText: String,
        val asDigit: String,
        val position: Int
    )
}