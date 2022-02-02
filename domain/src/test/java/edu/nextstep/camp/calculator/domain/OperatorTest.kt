package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat

class OperatorTest {
    @org.junit.Test
    fun `5와 2를 더하면 7이 된다`() {
        // when
        val result = Operator.PLUS.calculate(5f, 2f)

        // then
        assertThat(result).isEqualTo(7)
    }

    @org.junit.Test
    fun `5와 2를 빼면 3이 된다`() {
        // when
        val result = Operator.MINUS.calculate(5f, 2f)

        // then
        assertThat(result).isEqualTo(3)
    }

    @org.junit.Test
    fun `5와 2를 곱하면 10이 된다`() {
        // when
        val result = Operator.MULTIPLY.calculate(5f, 2f)

        // then
        assertThat(result).isEqualTo(10)
    }

    @org.junit.Test
    fun `5와 2를 나누면 2점5가 된다`() {
        // when
        val result = Operator.DIVIDE.calculate(5f, 2f)

        // then
        assertThat(result).isEqualTo(2.5f)
    }
}