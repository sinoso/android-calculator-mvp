package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }


    @ValueSource(
        strings = [
            "1", "2", "3", "4", "5", "6", "7", "8", "9",
        ]
    )
    @ParameterizedTest(name = "{0} 숫자를 입력하면 화면에 {0} 보여야 한다.")
    fun test1(input: Int) {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(input)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo(input.toString())
        verify { view.showExpression(actual) }
    }

    @Test
    @DisplayName("입력된 값이 없으면 계산시 토스트 메시지를 보여줘야 한다")
    fun test2() {
        // given
        val toastIdSlot = slot<Int>()
        every { view.showExpression(any()) } just Runs
        every { view.showToast(capture(toastIdSlot)) } just Runs

        // when
        presenter.calculateToExpression()

        // then
        val actual = toastIdSlot.captured
        assertThat(actual).isEqualTo(R.string.incomplete_expression)
        verify(exactly = 0) { view.showExpression(any()) }
    }

    @CsvSource(
        value = [
            "8,+,12",
            "8,-,4",
            "8,*,32",
            "8,/,2",
        ]
    )
    @ParameterizedTest(name = "불완전한 수식 {0} {1} 입력된 상태에서 계산하면 토스트 메시지를 보여줘야 한다")
    fun test3(operand: Int, operator: String, expected: String) {
        // given
        val toastIdSlot = slot<Int>()
        every { view.showExpression(any()) } just Runs
        every { view.showToast(capture(toastIdSlot)) } just Runs
        presenter.addToExpression(operand)
        presenter.addToExpression(Operator.of(operator)!!)

        // when
        presenter.calculateToExpression()

        // then
        val actual = toastIdSlot.captured
        assertThat(actual).isEqualTo(R.string.incomplete_expression)
        verify(exactly = 2) { view.showExpression(any()) }
    }

    @CsvSource(
        value = [
            "8,+,4,12",
            "8,-,4,4",
            "8,*,4,32",
            "8,/,4,2",
        ]
    )
    @ParameterizedTest(name = "완전한 수식 {0} {1} {2} 입력된 상태에서 계산하면 화면에 {3} 보여야 한다.")
    fun test4(operand1: Int, operator: String, operand2: Int, expected: String) {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } just Runs
        presenter.addToExpression(operand1)
        presenter.addToExpression(Operator.of(operator)!!)
        presenter.addToExpression(operand2)

        // when
        presenter.calculateToExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo(expected)
        verify(exactly = 4) { view.showExpression(any()) }
    }
}