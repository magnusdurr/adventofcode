package com.magnusdurr.adventofcode.twentyone

class BingoGame(val numbers: List<Int>, val boards: List<Board>) {

    companion object {
        fun loadFromFile(lines: List<String>): BingoGame {
            val numbers = lines.first().split(",").map { it.toInt() }

            val splitByWhitespace = "\\s+".toRegex()
            val boards = lines.drop(1)
                .filter { it.trim().isNotEmpty() }
                .chunked(5)
                .map {
                    it.flatMapIndexed { rowIndex, row ->
                        row.trim().split(splitByWhitespace).mapIndexed { columnIndex, value ->
                            BoardSquare(rowIndex, columnIndex, value.toInt())
                        }
                    }
                }.map { Board(it) }

            return BingoGame(numbers, boards)
        }
    }

    fun findWinner(): Pair<Board, Int> {
        numbers.forEach { number ->
            boards.forEach {
                it.check(number)
            }

            boards.find { it.hasBingo() }?.also {
                return it to number
            }
        }

        throw IllegalStateException("No winner!")
    }

    class Board(val grid: List<BoardSquare>) {
        fun check(number: Int) {
            grid.filter { it.value == number }
                .forEach { it.check() }
        }

        fun hasBingo(): Boolean {
            return rows().any { row -> row.all { it.checked } }
                    || columns().any { column -> column.all { it.checked } }
        }

        fun sumOfUnchecked(): Int = grid
            .filter { !it.checked }
            .map { it.value }
            .sum()

        private fun rows(): List<List<BoardSquare>> = (0..4).map {
            grid.filter { square -> square.row == it }
        }

        private fun columns(): List<List<BoardSquare>> = (0..4).map {
            grid.filter { square -> square.column == it }
        }
    }

    class BoardSquare(
        val row: Int,
        val column: Int,
        val value: Int,
        var checked: Boolean = false
    ) {
        fun check() {
            checked = true;
        }
    }
}