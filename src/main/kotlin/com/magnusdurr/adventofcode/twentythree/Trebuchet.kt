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

    internal fun textToNumbers(line: String): String {
        var result = line
        var firstTextDigit = firstTextDigitPosition(result)

        while (firstTextDigit != null) {
            result = result.replace(firstTextDigit.asText, firstTextDigit.asDigit)
            firstTextDigit = firstTextDigitPosition(result)
        }

        return result
    }

    private fun firstTextDigitPosition(result: String) =
        numbersAsText.map { TextDigitPosition(it.key, it.value, result.indexOf(it.key)) }
            .filter { it.position != -1 }
            .minByOrNull { it.position }

    internal data class TextDigitPosition(
        val asText: String,
        val asDigit: String,
        val position: Int
    )

    fun calibrationValueSum(lines: List<String>): Int = lines
        .map(::numberFromLine)
        .also { println(it) }
        .sum()

    fun calibrationValueSumIncludingText(lines: List<String>): Int = lines
        .map(::textToNumbers)
        .map(::numberFromLine)
        .also { println(it) }
        .sum()
}