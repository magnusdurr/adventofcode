package com.magnusdurr.adventofcode.twentyone

class FishSchool(initialFishes: List<Int>) {

    private val fish = initialFishes.map { LanternFish(it) }.toMutableList()

    fun size(): Int = fish.size

    fun simulateDays(days: Int) {
        (0 until days).forEach { _ ->
            val spawn = mutableListOf<LanternFish>()
            fish.forEach {
                it.simulateDay()?.also { child -> spawn.add(child) }
            }
            fish.addAll(spawn)
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