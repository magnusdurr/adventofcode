package com.magnusdurr.adventofcode.twentyone

class NavigationProtocolParser {

    companion object {
        val syntax = mapOf(
            '[' to ']',
            '{' to '}',
            '<' to '>',
            '(' to ')'
        )

        val errorScore = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )

        fun findBadLines(lines: List<String>): List<ParserException> = lines.mapNotNull {
            try {
                parse(it)
                null
            } catch (e: ParserException) {
                e
            }
        }

        fun parse(input: String): List<Chunk> {
            val stack = mutableListOf<Chunk>()
            stack.add(Chunk('['))

            input.forEach { i ->
                if (i in syntax.keys) {
                    stack.add(0, Chunk(i))
                } else if (i in syntax.values) {
                    stack[0].close(i)
                    val closed = stack.removeAt(0)
                    if (stack.isNotEmpty()) {
                        stack[0].addContent(closed)
                    }
                }
            }

            return stack.flatMap { if (it.content.isNotEmpty() && !it.closed) it.content else emptyList() }
        }

        fun countErrorScore(errors: List<ParserException>): Int {
            val errorCount = mutableMapOf<Char, Int>()

            errors.forEach {
                errorCount.compute(it.end) { _, value -> value?.plus(1) ?: 1 }
            }

            return errorCount.map {
                errorScore[it.key]!! * it.value
            }.sum()
        }
    }


    class Chunk(val start: Char) {
        var closed = false
        var content = mutableListOf<Chunk>()

        fun close(end: Char) {
            if (syntax[start] != end) {
                throw ParserException(start, end)
            }
            closed = true
        }

        fun addContent(item: Chunk) {
            content.add(item)
        }

        override fun toString(): String {
            return "${start}chunk${
                if (content.isNotEmpty()) content.joinToString(
                    separator = ",",
                    prefix = ":"
                ) { it.toString() } else ""
            }${syntax[start]}"
        }
    }

    class ParserException(val start: Char, val end: Char) : RuntimeException("Syntax error, $start was closed by $end")
}