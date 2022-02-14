package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.*
import edu.nextstep.camp.calculator.domain.CalculationMemory
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CalculatorPresenterTest {
    @MockK
    private lateinit var view: CalculatorContract.View

    @InjectMockKs
    private lateinit var presenter: CalculatorPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `빈 수식에 숫자가 입력되면 수식에 추가되고 수식을 갱신하는 함수를 호출한다`() {
        // given
        val expected = Expression(listOf(1))
        every { view.refreshExpression(expected) } answers { nothing }
        // when
        presenter.addExpressionElement(1)
        // then
        verify { view.refreshExpression(expected) }
    }

    @Test
    fun `빈 수식에 연산자가 입력되면 수식을 갱신하는 함수를 호출하지 않는다`() {
        // when :
        presenter.addExpressionElement(Operator.Plus)
        // then :
        verify(exactly = 0) { view.refreshExpression(any()) }
    }

    @Test
    fun `수식 1 에서 2를 추가하면 수식을 12로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter = CalculatorPresenter(view, Expression(listOf(1)))
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.addExpressionElement(2)
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("12")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `수식 1 에서 + 를 추가하면 수식을 1 + 로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter = CalculatorPresenter(view, Expression(listOf(1)))
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.addExpressionElement(Operator.Plus)
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1 +")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `수식 1 + 에서 -를 추가하면 수식을 1 - 로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter = CalculatorPresenter(view, Expression(listOf(1, Operator.Plus)))
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.addExpressionElement(Operator.Minus)
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1 -")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `수식 12 에서 마지막을 제거하면 수식을 1로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter = CalculatorPresenter(view, Expression(listOf(1, 2)))
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.removeLastExpressionElement()
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `1 +가 입력된 수식에서 마지막을 제거하면 뷰에 1 로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter = CalculatorPresenter(view, Expression(listOf(1, Operator.Plus)))
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.removeLastExpressionElement()
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("1")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `빈 수식에서 마지막을 제거하면 뷰에 갱신 함수를 호출하지 않는다`() {
        // given :
        // when :
        presenter.removeLastExpressionElement()
        // then :
        verify(exactly = 0) { view.refreshExpression(any()) }
    }

    @Test
    fun `완성된 수식에서 결과를 구하면 수식을 결과로 갱신하는 함수를 호출한다`() {
        // given :
        val expressionSlot = slot<Expression>()
        presenter = CalculatorPresenter(view, Expression(listOf(5, Operator.Multiply, 6)))
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }
        // when :
        presenter.calculateExpression()
        // then :
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("30")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `미완성 수식에서 결과를 구하면 수식을 갱신하는 함수는 호출하지 않고, 잘못된 수식을 알리는 함수를 호출한다`() {
        // given :
        presenter = CalculatorPresenter(view, Expression(listOf(5, Operator.Multiply)))
        // when :
        presenter.calculateExpression()
        // then :
        verify(exactly = 0) { view.refreshExpression(any()) }
        verify(exactly = 1) { view.notifyInCompleteExpression() }
    }

    @Test
    fun `완성된 수식에서 결과를 구하여 계산 메모리에 추가되면 갱신하는 함수를 호출한다`() {
        // given :
        val expression = Expression(listOf(5, Operator.Multiply, 6))
        val expectedMemory = listOf(CalculationMemory.Record(expression, 30))
        presenter = CalculatorPresenter(view, expression)
        every { view.refreshCalculationMemory(expectedMemory) } answers { nothing }
        // when :
        presenter.calculateExpression()
        // then :
        verify { view.refreshCalculationMemory(expectedMemory) }
    }

    @Test
    fun `계산 기록이 보이는 상태이면 기록을 보이지 않고 결과가 보이게 하는 함수를 호출한다`() {
        // given :
        // when :
        presenter.toggleCalculationMemory(true)
        // then :
        verify { view.hideCalculationMemory() }
        verify(exactly = 0) { view.showCalculationMemory() }
    }

    @Test
    fun `계산 기록이 보이지 않으면 기록을 보이고 결과가 보이지 않게 하는 함수를 호출한다`() {
        // given :
        // when :
        presenter.toggleCalculationMemory(false)
        // then :
        verify { view.showCalculationMemory() }
        verify(exactly = 0) { view.hideCalculationMemory() }
    }
}
