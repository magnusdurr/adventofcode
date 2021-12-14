package com.magnusdurr.adventofcode.twentyone

class Submarine {
    var horizontal = 0
    var depth = 0
    private var aim = 0

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
}