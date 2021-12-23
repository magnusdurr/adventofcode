package com.magnusdurr.adventofcode.twentyone

import com.magnusdurr.adventofcode.twentyone.NavigationProtocolParser.ParserException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class NavigationProtocolParserTest {

    val testInput = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]"
    )

    @Test
    fun `day ten - parse valid line`() {
        assertThat(NavigationProtocolParser.parse("[({(<(())[]>[[{[]{<()<>>")).hasSize(3)
    }

    @Test
    fun `day ten - parse invalid line`() {
        assertThrows(ParserException::class.java) {
            NavigationProtocolParser.parse("{([(<{}[<>[]}>{[]{[(<()>")
        }.also {
            assertThat(it.message).contains("[ was closed by }")
        }
    }

    @Test
    fun `day ten - find five invalid lines`() {
        val errors = NavigationProtocolParser.findBadLines(testInput)
        assertThat(NavigationProtocolParser.countErrorScore(errors)).isEqualTo(26397)
    }
}