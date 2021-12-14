package com.magnusdurr.adventofcode.twentyone

class Submarine {
    var horizontal = 0
    var depth = 0

    private var aim = 0
    private val engine = Engine()

    fun drive(direction: String, amount: Int) {
        when (direction) {
            "forward" -> {
                horizontal += amount
                depth += amount * aim
            }
            "down" -> aim += amount
            "up" -> aim -= amount
            else -> throw IllegalArgumentException("Unknown direction: $direction")
        }
    }

    fun position(): Int = horizontal * depth
    fun engineDiagnostics(readings: List<String>): Engine.Diagnostics = engine.diagnostics(readings)
}