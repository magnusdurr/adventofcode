package com.magnusdurr.adventofcode.twentyone

class Engine {
    fun diagnostics(readings: List<String>): Diagnostics {
        val bits = mutableMapOf<Int, Int>()

        readings.forEach {
            it.forEachIndexed { index, char ->
                bits.compute(index) { _, v ->
                    (v ?: 0) + char.toString().toInt()
                }
            }
        }

        val gamma = bits.values.joinToString("") { if (it > readings.size / 2) "1" else "0" }
        val epsilon = gamma.map { if (it == '0') '1' else '0' }.joinToString("")

        return Diagnostics(gamma.toInt(2), epsilon.toInt(2))
    }

    data class Diagnostics(
        val gamma: Int,
        val epsilon: Int
    ) {
        fun powerConsumption(): Int = gamma * epsilon
    }
}