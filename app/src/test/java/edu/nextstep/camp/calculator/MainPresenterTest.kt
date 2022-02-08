package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.lang.Exception

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var mockView: MainContract.View

    @BeforeEach
    fun setUp() {
        mockView = mockk()
        presenter = MainPresenter(mockView)
    }

    @ParameterizedTest(name = "{displayName}")
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    @DisplayName("초기상태에서 {0}숫자를 입력하면 입력한 {0} 숫자가 보인다.")
    fun whenDefaultStateInputOperandTest(operand: Int) {
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()

        presenter.addToExpression(operand)

        assertThat(expressionSlot.captured.toString()).isEqualTo(operand.toString())
    }

    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = ["+", "/", "*", "-"])
    @DisplayName("초기상태에서 {0}연산자를 입력하면 초기상태를 유지한다")
    fun whenDefaultStateInputOperatorTest(operatorArg: String) {
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()
        presenter.addToExpression(Operator.of(operatorArg)!!)

        assertThat(expressionSlot.captured).isEqualTo(Expression.EMPTY)
    }


    @ParameterizedTest(name = "{displayName}")
    @MethodSource("provideArgumentForSequenceInputOperandTest")
    @DisplayName("{0} 입력된 상태에서 피연산자 {1}를 입력하면 {2} 된다")
    fun sequenceInputOperandTest(initExpression: Expression, inputs: List<Int>, expected: String) {
        val presenterHasInitExpression = MainPresenter(mockView, expression = initExpression)
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()

        inputs.forEach {
            presenterHasInitExpression.addToExpression(it)
        }

        assertThat(expressionSlot.captured.toString()).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{displayName}")
    @MethodSource("provideArgumentForSequenceInputOperatorTest")
    @DisplayName("{0} 입력된 상태에서 연산자 {1}를 입력하면 {2} 된다")
    fun sequenceInputOperatorTest(initExpression: Expression, inputs: List<Operator>, expected: String) {
        val presenterHasInitExpression = MainPresenter(mockView, expression = initExpression)
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()

        inputs.forEach {
            presenterHasInitExpression.addToExpression(it)
        }

        assertThat(expressionSlot.captured.toString()).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{displayName}")
    @MethodSource("provideArgumentForDeleteTest")
    @DisplayName("{0} 입력된 상태에서 삭제(delete)하면 {1} 된다")
    fun deleteTest(initExpression: Expression, expected: String) {
        val presenterHasInitExpression = MainPresenter(mockView, expression = initExpression)
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()

        presenterHasInitExpression.delete()

        assertThat(expressionSlot.captured.toString()).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{displayName}")
    @MethodSource("provideArgumentForCalculateTest")
    @DisplayName("{0} 입력된 상태에서 계산하면 {1} 된다")
    fun calculateTest(initExpression: Expression, expected: String) {
        val presenterHasInitExpression = MainPresenter(mockView, expression = initExpression)
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()

        presenterHasInitExpression.calculate()

        assertThat(expressionSlot.captured.toString()).isEqualTo(expected)
    }


    @Test
    @DisplayName("유효하지 않은 수식을 계산하면 IllegalArgumentException 를 파라메터로 onError 메소드를 호출한다.  ")
    fun invalidExpressionCalculateTest() {
        val presenterHasInitExpression = MainPresenter(mockView, expression = Expression(listOf(1, Operator.Plus)))

        val exceptionSlot = slot<Exception>()
        val expressionSlot = slot<Expression>()
        every { mockView.showExpression(capture(expressionSlot)) } returns mockk()
        every { mockView.onError(capture(exceptionSlot)) } returns mockk()

        presenterHasInitExpression.calculate()

        assertThat(exceptionSlot.captured).isInstanceOf(IllegalArgumentException::class.java)
    }

    companion object {
        @JvmStatic
        private fun provideArgumentForSequenceInputOperandTest() = listOf(
            Arguments.of(Expression(listOf(12, Operator.Plus)), listOf(0), "12 + 0"),
            Arguments.of(Expression(listOf(12, Operator.Plus)), listOf(1, 2, 3), "12 + 123"),
            Arguments.of(Expression(listOf(123)), listOf(4), "1234"),
            Arguments.of(Expression(listOf(12, Operator.Minus)), listOf(4), "12 - 4"),
            Arguments.of(Expression(listOf(10)), listOf(0), "100"),
            Arguments.of(Expression(listOf(0)), listOf(0), "0"),
            Arguments.of(Expression(listOf(0)), listOf(1, 2), "12"),
        )

        @JvmStatic
        private fun provideArgumentForSequenceInputOperatorTest() = listOf(
            Arguments.of(Expression(listOf(12)), listOf(Operator.Plus), "12 +"),
            Arguments.of(Expression(listOf(12)), listOf(Operator.Minus), "12 -"),
            Arguments.of(Expression(listOf(12)), listOf(Operator.Divide), "12 /"),
            Arguments.of(Expression(listOf(12)), listOf(Operator.Multiply), "12 *"),
            Arguments.of(Expression(listOf(12, Operator.Plus)), listOf(Operator.Multiply), "12 *"),
            Arguments.of(Expression(listOf(12, Operator.Minus)), listOf(Operator.Plus), "12 +"),
            Arguments.of(Expression(listOf(12, Operator.Divide)), listOf(Operator.Plus, Operator.Minus), "12 -"),
            Arguments.of(Expression(listOf(0)), listOf(Operator.Plus), "0 +"),
        )

        @JvmStatic
        private fun provideArgumentForDeleteTest() = listOf(
            Arguments.of(Expression(listOf(12)), "1"),
            Arguments.of(Expression(listOf(1200)), "120"),
            Arguments.of(Expression(listOf(1)), Expression.EMPTY.toString()),
            Arguments.of(Expression(listOf(0)), Expression.EMPTY.toString()),
            Arguments.of(Expression(listOf(1, Operator.Minus)), "1"),
            Arguments.of(Expression(listOf(1, Operator.Minus, 1245)), "1 - 124"),
            Arguments.of(Expression(listOf(1500, Operator.Divide)), "1500"),
            Arguments.of(Expression(listOf(0, Operator.Multiply)), "0"),
        )

        @JvmStatic
        private fun provideArgumentForCalculateTest() = listOf(
            Arguments.of(Expression(listOf(12, Operator.Minus, 1)), "11"),
            Arguments.of(Expression(listOf(12, Operator.Minus, 1,  Operator.Plus, 100)), "111"),
            Arguments.of(Expression(listOf(12, Operator.Multiply, 2,  Operator.Plus, 100)), "124"),
            Arguments.of(Expression(listOf(12, Operator.Multiply, 2,  Operator.Divide, 100)), "0"),
            Arguments.of(Expression(listOf(1200, Operator.Multiply, 4,  Operator.Divide, 100)), "48"),
            Arguments.of(Expression(listOf(1200, Operator.Plus, 4,  Operator.Multiply, 2)), "2408"),
            Arguments.of(Expression(listOf(1200, Operator.Plus, 4,  Operator.Multiply, 2, Operator.Minus, 200)), "2208"),
            Arguments.of(Expression(listOf(1200, Operator.Plus, 4,  Operator.Multiply, 2, Operator.Minus, 3000)), "-592"),
            Arguments.of(Expression(listOf(1200, Operator.Plus, 4,  Operator.Multiply, 2, Operator.Minus, 3000, Operator.Plus, 100)), "-492"),
            Arguments.of(Expression(listOf(1200)), "1200")
        )
    }

}