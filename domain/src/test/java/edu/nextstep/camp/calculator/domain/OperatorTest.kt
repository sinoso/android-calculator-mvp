package edu.nextstep.camp.calculator.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OperatorTest {
    @Test
    fun `더하기`() {
        assertThat(Operator.of("+")).isEqualTo(Operator.Plus)
    }

    @Test
    fun `빼기`() {
        assertThat(Operator.of("-")).isEqualTo(Operator.Minus)
    }

    @Test
    fun `곱하기`() {
        assertThat(Operator.of("*")).isEqualTo(Operator.Multiply)
    }

    @Test
    fun `나누기`() {
        assertThat(Operator.of("/")).isEqualTo(Operator.Divide)
    }
}
