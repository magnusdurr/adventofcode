package com.magnusdurr.adventofcode.twentyone

class SevenSegmentDisplay(private val input: List<String>, private val numbers: List<String>) {

    companion object {
        fun sumOfUniqueNumbers(lines: List<String>): Int = lines.sumOf {
            fromInputLine(it).numberOfKnownNumbers()
        }

        fun sumOfOutputNumbers(lines: List<String>): Int = lines.sumOf {
            fromInputLine(it).decodeAndCombineNumbers()
        }

        private fun fromInputLine(line: String): SevenSegmentDisplay {
            val inputOutput = line.split(" | ")

            return SevenSegmentDisplay(
                inputOutput.first().split(" "),
                inputOutput.last().split(" ")
            )
        }
    }

    private val numbersMap = figureOutInputMapping()

    fun decodeAndCombineNumbers(): Int {
        return numbers.joinToString("") { numbersMap[Number(it)]!! }.toInt()
    }

    private fun figureOutInputMapping(): Map<Number, String> {
        val numbers = input.map { Number(it) }.toMutableList()

        val one = numbers.findUnique { it.length == 2 }.also { numbers.remove(it) }
        val seven = numbers.findUnique { it.length == 3 }.also { numbers.remove(it) }
        val four = numbers.findUnique { it.length == 4 }.also { numbers.remove(it) }
        val eight = numbers.findUnique { it.length == 7 }.also { numbers.remove(it) }

        val nine = numbers.filter { it.length == 6 }
            .findUnique { it.nonMatchingPositions(listOf(seven, four)) == 1 }.also { numbers.remove(it) }
        val zero = numbers.filter { it.length == 6 }
            .findUnique { one.nonMatchingPositions(it) == 0 }.also { numbers.remove(it) }
        val six = numbers.findUnique { it.length == 6 }.also { numbers.remove(it) }

        val three = numbers.filter { it.length == 5 }
            .findUnique { one.nonMatchingPositions(it) == 0 }.also { numbers.remove(it) }
        val five = numbers.filter { it.length == 5 }
            .findUnique { six.nonMatchingPositions(it) == 1 }.also { numbers.remove(it) }
        val two = numbers.findUnique { it.length == 5 }

        return mapOf(
            zero to "0",
            one to "1",
            two to "2",
            three to "3",
            four to "4",
            five to "5",
            six to "6",
            seven to "7",
            eight to "8",
            nine to "9"
        )
    }

    private fun numberOfKnownNumbers(): Int = numbers.filter { it.length in listOf(2, 3, 4, 7) }.size

    class Number(input: String) {
        val values = input.split("").filter { it.isNotEmpty() }.sorted()
        val length = values.size

        fun nonMatchingPositions(other: Number): Int = nonMatchingPositions(listOf(other))

        fun nonMatchingPositions(others: List<Number>): Int {
            return values.toMutableList().filter { it !in others.flatMap { num -> num.values }.toSet() }.size
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Number) return false

            if (length != other.length) return false
            if (values != other.values) return false

            return true
        }

        override fun hashCode(): Int {
            var result = values.hashCode()
            result = 31 * result + length
            return result
        }

        override fun toString(): String {
            return "Number(values=$values)"
        }
    }

    private inline fun <T> Iterable<T>.findUnique(predicate: (T) -> Boolean): T {
        val filtered = this.filter(predicate)
        if (filtered.size == 1) {
            return filtered.first()
        } else {
            throw IllegalStateException(" Predicate does not identify a unique element in the collection")
        }
    }
}