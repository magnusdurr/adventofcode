package com.magnusdurr.adventofcode.twentyone

import kotlin.math.abs

class HydrothermalVentScanner(private val ventLines: List<VentLine>) {

    companion object {
        fun loadInput(lines: List<String>): HydrothermalVentScanner {
            return HydrothermalVentScanner(lines.map {
                val startAndEnd = it.split(" -> ")
                VentLine(toPoint(startAndEnd.first()), toPoint(startAndEnd.last()))
            })
        }

        private fun toPoint(input: String): Point {
            val xy = input.split(",")
            return Point(xy.first().toInt(), xy.last().toInt())
        }
    }

    fun simpleHeatMap(heat: Int): Set<Point> {
        return createHeatMap(heat, ventLines.filter { it.isVertical() || it.isHorizontal() })
    }

    fun heatMap(heat: Int): Set<Point> {
        return createHeatMap(heat, ventLines)
    }

    private fun createHeatMap(heat: Int, lines: List<VentLine>): Set<Point> {
        val result = mutableMapOf<Point, Int>()

        lines.flatMap { it.points() }
            .forEach {
                result.compute(it) { _, v ->
                    v?.plus(1) ?: 1
                }
            }

        return result.filter { it.value >= heat }.keys
    }

    data class VentLine(
        val start: Point,
        val end: Point
    ) {
        fun isHorizontal(): Boolean = start.y == end.y
        fun isVertical(): Boolean = start.x == end.x

        fun points(): List<Point> = if (isHorizontal()) {
            rangeFromZero(end.x - start.x).map { Point(start.x + it, start.y) }
        } else if (isVertical()) {
            rangeFromZero(end.y - start.y).map { Point(start.x, start.y + it) }
        } else {
            assert(abs(end.x - start.x) == abs(end.y - start.y))
            val horizontalRange = rangeFromZero(end.x - start.x)
            val verticalIterator = rangeFromZero(end.y - start.y).iterator()

            horizontalRange.map {
                Point(start.x + it, start.y + verticalIterator.next())
            }
        }

        private fun rangeFromZero(target: Int) = if (target < 0) {
            0.downTo(target)
        } else {
            0..target
        }
    }

    data class Point(val x: Int, val y: Int)
}