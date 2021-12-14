package com.magnusdurr.adventofcode.twentyone

class Submarine {
    var horizontal: Int = 0
    var depth: Int = 0

    fun drive(direction: String, amount: Int) {
        when (direction) {
            "forward" -> horizontal += amount
            "down" -> depth += amount
            "up" -> depth -= amount
            else -> throw IllegalArgumentException("Unknown direction: $direction")
        }
    }

    fun position(): Int = horizontal * depth
}