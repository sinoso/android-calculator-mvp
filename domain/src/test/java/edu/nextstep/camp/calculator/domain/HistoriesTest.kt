package edu.nextstep.camp.calculator.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName

import org.junit.jupiter.api.Test

internal class HistoriesTest {
    private val calculator = Calculator()
    private lateinit var histories: Histories

    @BeforeEach
    fun setup() {
        histories = Histories()
    }

    @DisplayName("Expression 3 +5와 Result 8을 넣으면 History(3+5, 8)이 저장된다.")
    @Test
    fun plus() {
        // given
        val rawExpression = "3 + 5"
        val result = calculator.calculate(rawExpression)

        // when
        if (result != null) {
            histories += History(rawExpression, result)
        }
        val actual = histories.toList()
        val expected = listOf(History(rawExpression, 8))

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
