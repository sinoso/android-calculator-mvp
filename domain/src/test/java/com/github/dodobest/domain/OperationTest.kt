package com.github.dodobest.domain

import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Test

class OperationTest {
    @Test
    fun whenInputPlusString_thenShouldReturnPlusOperation() {
        // when : 사칙 연산 기호 +를 나타내는 문자열을 넣으면
        val actual: Operation = Operation.convertToOperation("+")

        // then : 사칙연산자 +를 나타내는 Operation 을 return 한다
        Truth.assertThat(actual).isEqualTo(Operation.PLUS)
    }

    @Test
    fun whenInputNotArithmeticString_thenThrowIllegalArgumentException() {
        // when : 사용자가 사칙연산 식에 빈 문자열을 입력하면
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { Operation.convertToOperation("&") }

        // then : IllegalArgumentException 을 발생시킨다
        Truth.assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
        Truth.assertThat(thrown).hasMessageThat().contains("연산자를 찾을 수 없습니다")
    }
}