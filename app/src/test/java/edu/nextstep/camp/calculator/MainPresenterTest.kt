package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @Before
    fun initialize() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @Test
    fun `빈 수식일 때 피연산자를 추가 할 수 있다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(3)

        // then
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("3")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `수식에 3이 입력 되어 있을때 5를 입력하면 35가 나타나야 한다`() {
        // given
        presenter = MainPresenter(view, Expression(3))
        val expressionSlot = slot<Expression>()
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(5)

        // then
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("35")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `수식에 '3' 이 입력 되어 있을때 '+' 를 입력하면 '3 +' 가 나타나야 한다`() {
        // given
        presenter = MainPresenter(view, Expression(3))
        val expressionSlot = slot<Expression>()
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(Operator.Plus)

        // then
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("3 +")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `빈 수식일 때 마지막을 제거해도 화면을 갱신하지 않는다`() {
        // when
        presenter.clear()

        // then
        verify(exactly = 0) { view.refreshExpression(any()) }
    }

    @Test
    fun `수식에 45가 입력 되어 있을 때 마지막을 제거하면 4가 남는다`() {
        // given
        presenter = MainPresenter(view, Expression(45))
        val expressionSlot = slot<Expression>()
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.removeLastOfExpression()

        // then
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("4")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `수식에 4 + 가 입력 되어 있을 때 마지막을 제거하면 4가 남는다`() {
        // given
        presenter = MainPresenter(view, Expression(4, Operator.Plus))
        val expressionSlot = slot<Expression>()
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.removeLastOfExpression()

        // then
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("4")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `수식에 '4 +' 가 입력 되어 있을 때 -를 입력하면 '4 -' 로 변경된다`() {
        // given
        presenter = MainPresenter(view, Expression(4, Operator.Plus))
        val expressionSlot = slot<Expression>()
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(Operator.Minus)

        // then
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("4 -")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `빈 수식일 때 연산자를 입력 해도 화면을 갱신하지 않는다`() {
        // when
        presenter.addToExpression(Operator.Multiply)

        // then
        verify(exactly = 0) { view.refreshExpression(any()) }
    }

    @Test
    fun `입력한 수식이 완전할 때 결과를 구하면 해당 결과로 화면이 갱신된다`() {
        // given
        presenter = MainPresenter(view, Expression(20, Operator.Divide, 5))
        val expressionSlot = slot<Expression>()
        every { view.refreshExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.calculateExpression()

        // then
        val actualExpression = expressionSlot.captured
        assertThat(actualExpression.toString()).isEqualTo("4")
        verify { view.refreshExpression(actualExpression) }
    }

    @Test
    fun `입력한 수식이 완전하지 않을 때 결과를 구하면 화면을 갱신하지 않고, 완성되지 않은 수식 임을 알린다`() {
        // given
        presenter = MainPresenter(view, Expression(30, Operator.Divide))

        // when
        presenter.calculateExpression()

        // then
        verify(exactly = 0) { view.refreshExpression(any()) }
        verify(exactly = 1) { view.notifyIncompleteExpression() }
    }
}
