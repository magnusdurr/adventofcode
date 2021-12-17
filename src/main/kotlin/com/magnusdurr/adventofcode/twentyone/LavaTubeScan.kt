package com.magnusdurr.adventofcode.twentyone

class LavaTubeScan(input: List<List<Int>>) {

    private val points: List<List<Point>> = input.mapIndexed { y, row ->
        row.mapIndexed { x, item ->
            Point(x, y, item)
        }
    }

    private val horizontalSize = input.first().size
    private val verticalSize = input.size

    companion object {
        fun parse(lines: List<String>): LavaTubeScan = LavaTubeScan(lines.map { row ->
            row.split("").filter { it.trim().isNotEmpty() }.map { it.toInt() }
        })
    }

    fun findLowPoints(): List<Point> {
        val lowPoints = mutableListOf<Point>()

        for (y in 0 until verticalSize) {
            for (x in 0 until horizontalSize) {
                if (isLowPoint(points[y][x])) {
                    lowPoints.add(points[y][x])
                }
            }
        }

        return lowPoints
    }

    fun multipliedSizeOfThreeLargestBasins(lowPoints: List<Point>): Int =
        lowPoints.asSequence()
            .map { exploreBasin(it, emptyList()) }
            .map { it.size }
            .sortedDescending()
            .take(3)
            .reduce { acc, i -> acc * i }

    fun exploreBasin(lowPoint: Point, explored: List<Point>): Set<Point> {
        val result = mutableListOf(lowPoint)

        result.addAll(
            neighbours(lowPoint)
                .filter { it.value > lowPoint.value && it.value < 9 && it !in explored }
                .flatMap { exploreBasin(it, result) }
        )

        return result.toSet()
    }

    private fun isLowPoint(point: Point): Boolean {
        return neighbours(point).minOf { it.value } > point.value
    }

    private fun neighbours(point: Point): List<Point> {
        val neighbours = mutableListOf<Point>()

        if (point.x > 0) {
            neighbours.add(points[point.y][point.x - 1])
        }
        if (point.x < horizontalSize - 1) {
            neighbours.add(points[point.y][point.x + 1])
        }

        if (point.y > 0) {
            neighbours.add(points[point.y - 1][point.x])
        }
        if (point.y < verticalSize - 1) {
            neighbours.add(points[point.y + 1][point.x])
        }

        return neighbours
    }

    data class Point(val x: Int, val y: Int, val value: Int, val riskLevel: Int = value + 1)
}