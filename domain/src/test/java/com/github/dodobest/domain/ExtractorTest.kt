package com.github.dodobest.domain

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class ExtractorTest {
    private lateinit var extractor: Extractor

    @Before
    fun setUp() {
        extractor = Extractor()
    }

    @Test
    fun whenInputPlusStatement_thenSplitNumAndSign() {
        // when : 사용자가 덧셈 식을 입력하면
        val answer: List<String> = listOf("1", "+", "2", "+", "3")
        val actual: List<String> = extractor.extractNumAndSignAll("1+2+3")

        // then : 숫자와 사칙연산 기호로 분리한다
        Truth.assertThat(actual).isEqualTo(answer)
    }

    @Test
    fun whenInputMinusStatement_thenSplitNumAndSign() {
        // when : 사용자가 뺄셈 식을 입력하면
        val answer: List<String> = listOf("10", "-", "2", "-", "3")
        val actual: List<String> = extractor.extractNumAndSignAll("10-2-3")

        // then : 숫자와 사칙연산 기호로 분리한다
        Truth.assertThat(actual).isEqualTo(answer)
    }

    @Test
    fun whenInputMultiplyStatement_thenSplitNumAndSign() {
        // when : 사용자가 곱셈 식을 입력하면
        val answer: List<String> = listOf("10", "*", "-5", "*", "9")
        val actual: List<String> = extractor.extractNumAndSignAll("10*-5*9")

        // then : 숫자와 사칙연산 기호로 분리한다
        Truth.assertThat(actual).isEqualTo(answer)
    }

    @Test
    fun whenInputDivideStatementWhichSplitApart_thenSplitNumAndSign() {
        // when : 사용자가 나누어 떨어지는 나눗셈 식을 입력하면
        val answer: List<String> = listOf("120", "/", "2", "/", "3")
        val actual: List<String> = extractor.extractNumAndSignAll("120/2/3")

        // then : 숫자와 사칙연산 기호로 분리한다
        Truth.assertThat(actual).isEqualTo(answer)
    }

    @Test
    fun whenInputDivideStatementWhichNotSplitApart_thenSplitNumAndSign() {
        // when : 사용자가 나누어 떨어지지 않는 나눗셈 식을 입력하면
        val answer: List<String> = listOf("10", "/", "3")
        val actual = extractor.extractNumAndSignAll("10/3")

        // then : 숫자와 사칙연산 기호로 분리한다
        Truth.assertThat(actual).isEqualTo(answer)
    }

    @Test
    fun whenInputAllArithmeticStatement_thenSplitNumAndSign() {
        // when : 사용자가 사칙연산을 모두 포함하는 사칙연산 식을 입력하면
        val answer: List<String> = listOf("2", "+", "3", "*", "4", "/", "2")
        val actual: List<String> = extractor.extractNumAndSignAll("2+3*4/2")

        // then : 숫자와 사칙연산 기호로 분리한다
        Truth.assertThat(actual).isEqualTo(answer)
    }

    @Test
    fun whenInputPositiveSignNumber_thenConsiderPositiveSignAsNothing() {
        // when : +가 붙은 양수를 입력하면
        val answer: List<String> = listOf("10", "+", "10", "/", "20")
        val actual: List<String> = extractor.extractNumAndSignAll("+10+10/+20")

        // then : 숫자와 사칙연산 기호로 분리한다
        Truth.assertThat(actual).isEqualTo(answer)
    }
}