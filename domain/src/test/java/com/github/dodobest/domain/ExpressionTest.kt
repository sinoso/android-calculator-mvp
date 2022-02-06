package com.github.dodobest.domain

import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ExpressionTest {
    private lateinit var expression: Expression

    @Before
    fun setUp() {
        expression = Expression()
    }

    @Test
    fun `문자열을 입력하면 빈 공백을 없앤 문자열을 반환한다`() {
        // when : 사용자가 문자열을 입력하면
        val actual: String = expression.eraseBlank("1 + 2 +3")

        // then : 빈 공백을 없앤 문자열을 반환한다
        Truth.assertThat(actual).isEqualTo("1+2+3")
    }

    @Test
    fun whenInputNull_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 null을 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("120+null/3") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("사칙 연산 외 기호가 입력되었습니다.")

    }

    @Test
    fun whenInputBlank_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 빈 문자열을 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("120/10- *3+ /1") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("연산 기호가 연속으로 입력되었습니다")
    }

    @Test
    fun whenInputOperationWhichIsNotArithmetic_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 사칙연산이 아닌 기호를 포함하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("5$7~3x8") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("사칙 연산 외 기호가 입력되었습니다.")
    }

    @Test
    fun whenInputOperationConsecutively_thenThrowIllegalArgumentException() {
        // 사용자가 사칙연산 기호를 연속으로 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("10+ 2 /- 3 * - + 2") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("연산 기호가 연속으로 입력되었습니다")
    }

    @Test
    fun whenInputMinusOperationConsecutively_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 기호 -를 연속으로 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("10+2---+---2") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("연산 기호가 연속으로 입력되었습니다")
    }

    @Test
    fun whenInputPlusOperationConsecutively_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 기호 +를 연속으로 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("10+2+++-+++2") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("연산 기호가 연속으로 입력되었습니다")
    }

    @Test
    fun whenInputNumberDividedByZero_thenThrowIllegalArgumentException() {
        // when : 사용자가 0으로 나누는 사칙연산 식을 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("1/0") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("0으로 나누는 값은 존재하지 않습니다")
    }

    @Test
    fun whenInputBracket_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 괄호를 포함하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("2+3*(5-3)") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("사칙 연산 외 기호가 입력되었습니다.")
    }

    @Test
    fun whenInputNumberWhichStartWithZero_thenThrowIllegalArgumentException() {
        // when : 사용자가 0으로 시작하는 숫자를 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { expression.evaluate("10+010") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("0으로 시작하는 숫자는 지원하지 않습니다")
    }
}