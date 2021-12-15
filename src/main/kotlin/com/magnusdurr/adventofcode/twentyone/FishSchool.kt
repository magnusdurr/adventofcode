package com.magnusdurr.adventofcode.twentyone

class FishSchool(initialFishes: List<Int>) {

    private val fish = initialFishes.map { LanternFish(it) }.toMutableList()
    private var fishLowMem = (0..8).map { SpawnCycle(it, 0) }.toMutableList()

    init {
        initialFishes.forEach { initial ->
            fishLowMem.find { it.timer == initial }!!.addSpawn(1)
        }
    }

    fun size(): Int = fish.size
    fun lowMemSize(): Long = fishLowMem.sumOf { it.number }

    fun simulateDays(days: Int) {
        (0 until days).forEach { _ ->
            val spawn = mutableListOf<LanternFish>()
            fish.forEach {
                it.simulateDay()?.also { child -> spawn.add(child) }
            }
            fish.addAll(spawn)
        }
    }

    fun simulateDaysLowMem(days: Int) {
        (0 until days).forEach { day ->
            val spawn = fishLowMem.sumOf { it.simulateDay() }
            fishLowMem.add(SpawnCycle(8, spawn))

            val consolidated = fishLowMem.filter { it.timer == 6 }.reduce { a, b ->
                a.addSpawn(b.number)
            }

            fishLowMem.removeIf { it.timer == 6 }
            fishLowMem.add(consolidated)
        }
    }

    class SpawnCycle(var timer: Int, var number: Long) {
        fun simulateDay(): Long = if (timer == 0) {
            timer = 6
            number
        } else {
            timer--
            0
        }

        fun addSpawn(children: Long): SpawnCycle {
            number += children
            return this
        }

        override fun toString(): String {
            return "SpawnCycle(timer=$timer, number=$number)"
        }
    }

    class LanternFish(var timer: Int = 8) {
        fun simulateDay(): LanternFish? = if (timer == 0) {
            timer = 6
            LanternFish()
        } else {
            timer--
            null
        }
    }
}