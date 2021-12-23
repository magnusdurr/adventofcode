package com.magnusdurr.adventofcode.twentyone

import com.magnusdurr.adventofcode.twentyone.NavigationProtocolParser.Companion.closingSequence
import com.magnusdurr.adventofcode.twentyone.NavigationProtocolParser.Companion.countClosingScore
import com.magnusdurr.adventofcode.twentyone.NavigationProtocolParser.Companion.countErrorScore
import com.magnusdurr.adventofcode.twentyone.NavigationProtocolParser.Companion.findBadLines
import com.magnusdurr.adventofcode.twentyone.NavigationProtocolParser.Companion.findClosingSequences
import com.magnusdurr.adventofcode.twentyone.NavigationProtocolParser.ParserException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class NavigationProtocolParserTest {

    private val testInput = listOf(
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
    fun `day ten - parse invalid line`() {
        assertThrows(ParserException::class.java) {
            NavigationProtocolParser.parse("{([(<{}[<>[]}>{[]{[(<()>")
        }.also {
            assertThat(it.message).contains("[ was closed by }")
        }
    }

    @Test
    fun `day ten - find five invalid lines`() {
        assertThat(countErrorScore(findBadLines(testInput))).isEqualTo(26397)
    }

    @Test
    fun `day ten - closing sequence`() {
        assertThat(closingSequence("[({(<(())[]>[[{[]{<()<>>")).isEqualTo("}}]])})]")
        assertThat(closingSequence("[(()[<>])]({[<{<<[]>>(")).isEqualTo(")}>]})")
        assertThat(closingSequence("(((({<>}<{<{<>}{[]{[]{}")).isEqualTo("}}>}>))))")
        assertThat(closingSequence("{<[[]]>}<{[{[{[]{()[[[]")).isEqualTo("]]}}]}]}>")
        assertThat(closingSequence("<{([{{}}[<[[[<>{}]]]>[]]")).isEqualTo("])}>")
    }

    @Test
    fun `day ten - closing sequence scores`() {
        assertThat(countClosingScore("}}]])})]")).isEqualTo(288957)
        assertThat(countClosingScore(")}>]})")).isEqualTo(5566)
        assertThat(countClosingScore("}}>}>))))")).isEqualTo(1480781)
        assertThat(countClosingScore("]]}}]}]}>")).isEqualTo(995444)
        assertThat(countClosingScore("])}>")).isEqualTo(294)
    }

    @Test
    fun `day ten - task two`() {
        assertThat(findClosingSequences(testInput).map { countClosingScore(it) }.middle()).isEqualTo(288957)
    }
}
