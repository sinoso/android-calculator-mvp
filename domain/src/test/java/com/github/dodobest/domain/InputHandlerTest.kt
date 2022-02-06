package com.github.dodobest.domain

import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class InputHandlerTest {
    private lateinit var inputHandler: InputHandler

    @Before
    fun setUp() {
        inputHandler = InputHandler()
    }

    @Test
    fun `InputHandler에 이전에 아무 값도 입력하지 않고 사칙연산 문자열을 입력하면 아무 변화가 없다`() {
        // when
        inputHandler.handleInputArithmetic("+")
        val actual: String = inputHandler.getString()

        // then
        Truth.assertThat(actual).isEqualTo("")
    }

    @Test
    fun `InputHandler에 1, +, -, 곱하기, 나누기, +, 3, -, 2를 입력하면 1+3-2 문자열이 저장된다`() {
        // when
        inputHandler.handleInputNum("1")
        inputHandler.handleInputArithmetic("+")
        inputHandler.handleInputArithmetic("-")
        inputHandler.handleInputArithmetic("*")
        inputHandler.handleInputArithmetic("/")
        inputHandler.handleInputArithmetic("+")
        inputHandler.handleInputNum("3")
        inputHandler.handleInputArithmetic("-")
        inputHandler.handleInputNum("2")
        val actual: String = inputHandler.getString()

        // then
        Truth.assertThat(actual).isEqualTo("1+3-2")
    }

    @Test
    fun `InputHandler에 1+20을 입력하고 지우기를 누르면 맨 뒤의 문자열부터 지워진다`() {
        // given
        inputHandler.handleInputNum("1")
        inputHandler.handleInputArithmetic("+")
        inputHandler.handleInputNum("2")
        inputHandler.handleInputNum("0")

        // when
        inputHandler.handleInputDelete()
        var actual: String = inputHandler.getString()

        // then
        Truth.assertThat(actual).isEqualTo("1+2")

        // when
        inputHandler.handleInputDelete()
        actual = inputHandler.getString()

        // then
        Truth.assertThat(actual).isEqualTo("1+")


        // when
        inputHandler.handleInputDelete()
        actual = inputHandler.getString()

        // then
        Truth.assertThat(actual).isEqualTo("1")

        // when
        inputHandler.handleInputDelete()
        actual = inputHandler.getString()

        // then
        Truth.assertThat(actual).isEqualTo("")

        // when
        inputHandler.handleInputDelete()
        actual = inputHandler.getString()

        // then
        Truth.assertThat(actual).isEqualTo("")
    }

    @Test
    fun `InputHandler에 1+2×3÷3를 입력하면 =를 누르면 3이 저장된다`() {
        // when
        inputHandler.handleInputNum("1")
        inputHandler.handleInputArithmetic("+")
        inputHandler.handleInputNum("2")
        inputHandler.handleInputArithmetic("*")
        inputHandler.handleInputNum("3")
        inputHandler.handleInputArithmetic("/")
        inputHandler.handleInputNum("3")
        inputHandler.handleEquals()
        val actual: String = inputHandler.getString()

        // then
        Truth.assertThat(actual).isEqualTo("3")
    }
}