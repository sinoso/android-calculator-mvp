package com.github.dodobest.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun whenInputPlusStatement_thenShouldShowRightResult() {
        // when : 사용자가 덧셈 식을 입력하면
        val inputArray: List<String> = listOf("1", "+", "2", "+", "3")
        val actual = calculator.calculate(inputArray)

        // then : 올바른 덧셈 결과를 계산한다
        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun whenInputMinusStatement_thenShouldShowRightResult() {
        // when : 사용자가 뺄셈 식을 입력하면
        val inputArray: List<String> = listOf("10", "-", "2", "-", "3")
        val actual = calculator.calculate(inputArray)

        // then : 올바른 뺄셈 결과를 계산한다
        assertThat(actual).isEqualTo(5)
    }

    @Test
    fun whenInputMultiplyStatement_thenShouldShowRightResult() {
        // when : 사용자가 곱셈 식을 입력하면
        val inputArray: List<String> = listOf("10", "*", "-5", "*", "9")
        val actual = calculator.calculate(inputArray)

        // then : 올바른 곱셈 결과를 계산한다
        assertThat(actual).isEqualTo(-450)
    }

    @Test
    fun whenInputDivideStatementWhichSplitApart_thenShouldShowIntegerResult() {
        // when : 사용자가 나누어 떨어지는 나눗셈 식을 입력하면
        val inputArray: List<String> = listOf("120", "/", "2", "/", "3")
        val actual = calculator.calculate(inputArray)

        // then : 올바른 나눗셈 결과를 계산한다
        assertThat(actual).isEqualTo(20)
    }

    @Test
    fun whenInputDivideStatementWhichNotSplitApart_thenShouldShowRealNumberResult() {
        // when : 사용자가 나누어 떨어지지 않는 나눗셈 식을 입력하면
        val inputArray: List<String> = listOf("10", "/", "3")
        val actual = calculator.calculate(inputArray)

        // then : 올바른 나눗셈 결과를 계산한다
        assertThat(actual).isWithin(1.0e-5).of(3.3333333)
    }

    @Test
    fun whenInputAllArithmeticStatement_thenShouldShowRightResult() {
        // when : 사용자가 사칙연산을 모두 포함하는 사칙연산 식을 입력하면
        val inputArray: List<String> = listOf("2", "+", "3", "*", "4", "/", "2")
        val actual = calculator.calculate(inputArray)

        // then : 올바른 계산 결과를 계산한다
        assertThat(actual).isEqualTo(10)
    }
}