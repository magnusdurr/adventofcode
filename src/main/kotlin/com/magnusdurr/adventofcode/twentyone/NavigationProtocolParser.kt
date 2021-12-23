package com.magnusdurr.adventofcode.twentyone

class NavigationProtocolParser {

    companion object {
        val syntax = mapOf(
            '[' to ']',
            '{' to '}',
            '<' to '>',
            '(' to ')'
        )

        private val errorScore = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )

        private val closeScore = mapOf(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4
        )

        fun findBadLines(lines: List<String>): List<ParserException> = lines.mapNotNull {
            try {
                parse(it)
                null
            } catch (e: ParserException) {
                e
            }
        }

        fun findClosingSequences(lines: List<String>): List<String> = lines.mapNotNull {
            try {
                closingSequence(it)
            } catch (e: ParserException) {
                null
            }
        }

        fun closingSequence(line: String): String = parse(line)
            .joinToString("") { "${it.isClosedBy()}" }

        fun parse(input: String): MutableList<Chunk> {
            val stack = mutableListOf<Chunk>()

            input.forEach { i ->
                if (i in syntax.keys) {
                    stack.add(0, Chunk(i))
                } else if (i in syntax.values) {
                    stack[0].close(i)
                    stack.removeAt(0)
                }
            }
            return stack
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

        fun countClosingScore(closingChars: String): Long = closingChars
            .map { closeScore[it]!!.toLong() }
            .reduce { result, it ->
                result * 5 + it
            }
    }


    class Chunk(val start: Char) {
        var closed = false
        var content = mutableListOf<Chunk>()

        fun close(end: Char) {
            if (isClosedBy() != end) {
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

        fun isClosedBy(): Char = syntax[start]!!
    }

    class ParserException(start: Char, val end: Char) : RuntimeException("Syntax error, $start was closed by $end")
}

fun List<Long>.middle(): Long {
    if (this.size % 2 == 0) {
        throw IllegalStateException("List with even number of entries does not have a middle element")
    }
    return this.sorted()[this.size / 2]
}