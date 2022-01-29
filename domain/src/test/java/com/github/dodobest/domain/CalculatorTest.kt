package com.github.dodobest.domain

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


class CalculatorTest {
    private val calculator = Calculator()
    private val expression = Expression()

    @Test
    fun whenInputPlusStatement_thenShouldShowRightResult() {
        // when : 사용자가 덧셈 식을 입력하면
        val actual: Double = calculator.calculate(expression.evaluate("1+2+3"))

        // then : 올바른 덧셈 결과를 계산한다
        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun whenInputMinusStatement_thenShouldShowRightResult() {
        // when : 사용자가 뺄셈 식을 입력하면
        val actual: Double = calculator.calculate(expression.evaluate("10-2-3"))

        // then : 올바른 뺄셈 결과를 계산한다
        assertThat(actual).isEqualTo(5)
    }

    @Test
    fun whenInputMultiplyStatement_thenShouldShowRightResult() {
        // when : 사용자가 곱셈 식을 입력하면
        val actual: Double = calculator.calculate(expression.evaluate("10*-5*9"))

        // then : 올바른 곱셈 결과를 계산한다
        assertThat(actual).isEqualTo(-450)
    }

    @Test
    fun whenInputDivideStatementWhichSplitApart_thenShouldShowIntegerResult() {
        // when : 사용자가 나누어 떨어지는 나눗셈 식을 입력하면
        val actual: Double = calculator.calculate(expression.evaluate("120/2/3"))

        // then : 올바른 나눗셈 결과를 계산한다
        assertThat(actual).isEqualTo(20)
    }

    @Test
    fun whenInputDivideStatementWhichNotSplitApart_thenShouldShowRealNumberResult() {
        // when : 사용자가 나누어 떨어지지 않는 나눗셈 식을 입력하면
        val actual = calculator.calculate(expression.evaluate("10/3"))

        // then : 올바른 나눗셈 결과를 계산한다
        assertThat(actual).isWithin(1.0e-5).of(3.3333333)
    }

    @Test
    fun whenInputAllArithmeticStatement_thenShouldShowRightResult() {
        // when : 사용자가 사칙연산을 모두 포함하는 사칙연산 식을 입력하면
        val actual: Double = calculator.calculate(expression.evaluate("2 + 3 * 4 / 2"))

        // then : 올바른 계산 결과를 계산한다
        assertThat(actual).isEqualTo(10)
    }

    @Test
    fun whenInputPositiveSignNumber_thenConsiderPositiveSignAsNothing() {
        // when : +가 붙은 양수를 입력하면
        val actual: Double = calculator.calculate(expression.evaluate("+10+10/+20"))

        // then : 올바른 계산 결과를 계산한다
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun whenInputNull_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 null을 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("120+null/3")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("사칙 연산 외 기호가 입력되었습니다.")

    }

    @Test
    fun whenInputBlank_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 빈 문자열을 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("120/10- *3+ /1")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("연산 기호가 연속으로 입력되었습니다")
    }

    @Test
    fun whenInputOperationWhichIsNotArithmetic_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 사칙연산이 아닌 기호를 포함하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("5$7~3x8")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("사칙 연산 외 기호가 입력되었습니다.")
    }

    @Test
    fun whenInputOperationConsecutively_thenThrowIllegalArgumentException() {
        // 사용자가 사칙연산 기호를 연속으로 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("10+ 2 /- 3 * - + 2")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("연산 기호가 연속으로 입력되었습니다")
    }

    @Test
    fun whenInputMinusOperationConsecutively_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 기호 -를 연속으로 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("10+2---+---2")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("연산 기호가 연속으로 입력되었습니다")
    }

    @Test
    fun whenInputPlusOperationConsecutively_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 기호 +를 연속으로 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("10+2+++-+++2")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("연산 기호가 연속으로 입력되었습니다")
    }

    @Test
    fun whenInputNumberDividedByZero_thenThrowIllegalArgumentException() {
        // when : 사용자가 0으로 나누는 사칙연산 식을 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("1/0")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("0으로 나누는 값은 존재하지 않습니다")
    }

    @Test
    fun whenInputBracket_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 괄호를 포함하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("2+3*(5-3)")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("사칙 연산 외 기호가 입력되었습니다.")
    }

    @Test
    fun whenInputNumberWhichStartWithZero_thenThrowIllegalArgumentException() {
        // when : 사용자가 0으로 시작하는 숫자를 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { calculator.calculate(expression.evaluate("10+010")) }

        // then : IllegalArgumentException 을 발생시킨다
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(thrown).hasMessageThat().contains("0으로 시작하는 숫자는 지원하지 않습니다")
    }
}