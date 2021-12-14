package com.magnusdurr.adventofcode.twentyone

class Engine {
    fun diagnostics(readings: List<String>): Diagnostics {
        val gamma = bitFilterByMostBitsPerPosition(readings)
        val oxygenRating = findRating(readings) { a, b -> a == b }
        val co2ScrubberRating = findRating(readings) { a, b -> a != b }

        return Diagnostics(
            gamma.toInt(2),
            oxygenRating.toInt(2),
            co2ScrubberRating.toInt(2)
        )
    }

    private fun bitFilterByMostBitsPerPosition(readings: List<String>): String {
        val bits = mutableMapOf<Int, Int>()

        readings.forEach {
            it.forEachIndexed { index, char ->
                bits.compute(index) { _, v ->
                    (v ?: 0) + char.toString().toInt()
                }
            }
        }

        return bits.values.joinToString("") { if (it >= readings.size.toDouble() / 2) "1" else "0" }
    }

    private fun findRating(readings: List<String>, predicate: (Char, Char) -> Boolean): String {
        var result = readings

        for (i in 0..readings.first().length) {
            val filter = bitFilterByMostBitsPerPosition(result)
            result = result.filter { predicate.invoke(it[i], filter[i]) }
            if (result.size == 1) {
                break
            }
        }

        return result.first()
    }

    data class Diagnostics(
        val gamma: Int,
        val oxygenRating: Int,
        val co2ScrubberRating: Int
    ) {
        val epsilon: Int = gamma
            .toString(2)
            .map { if (it == '0') '1' else '0' }
            .joinToString("")
            .toInt(2)

        fun powerConsumption(): Int = gamma * epsilon
        fun lifeSupportRating(): Int = oxygenRating * co2ScrubberRating
    }
}