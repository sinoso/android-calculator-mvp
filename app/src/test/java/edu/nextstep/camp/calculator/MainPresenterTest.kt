package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import com.joseph.domain.Expression
import com.joseph.domain.Operator
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    lateinit var presenter: MainPresenter
    lateinit var view: MainContract.View

    @Before
    fun initialize() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.displayExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.addExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1")
        verify { view.displayExpression(actual) }
    }

    @Test
    fun `빈 수식에 연산자를 추가했을때 화면에 아무것도 입력되지 않는다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.displayExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.addExpression(Operator.Plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("")
        verify { view.displayExpression(actual) }
    }

    @Test
    fun `3 + 에서 *를 입력하면 3 * 로 바뀐다`() {
        // given
        presenter = MainPresenter(view, Expression(listOf(3, Operator.Plus)))
        val expressionSlot = slot<Expression>()
        every { view.displayExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.addExpression(Operator.Multiply)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("3 *")
        verify { view.displayExpression(actual) }
    }

    @Test
    fun `3 + 4 에서 삭제하면 3 + 가 된다`() {
        // given
        presenter = MainPresenter(view, Expression(listOf(3, Operator.Plus, 4)))
        val expressionSlot = slot<Expression>()
        every { view.displayExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.removeAtLastExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("3 +")
        verify { view.displayExpression(actual) }
    }

    @Test
    fun `3 + 44 에서 삭제하면 3 + 4 가 된다`() {
        // given
        presenter = MainPresenter(view, Expression(listOf(3, Operator.Plus, 44)))
        val expressionSlot = slot<Expression>()
        every { view.displayExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.removeAtLastExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("3 + 4")
        verify { view.displayExpression(actual) }
    }

    @Test
    fun `3 + 4 * 2 를 계산하면 14가 나온다`() {
        // given
        presenter = MainPresenter(view, Expression(listOf(3, Operator.Plus, 4, Operator.Multiply, 2)))
        val expressionSlot = slot<Expression>()
        every { view.displayExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("14")
        verify { view.displayExpression(actual) }
    }

    @Test
    fun `빈 수식을 계산하려고 하면 토스트 메세지를 띄운다`() {
        presenter = MainPresenter(view, Expression(listOf(3, Operator.Minus)))

        presenter.calculate()

        verify(exactly = 1) { view.showIncompleteExpressionToast() }
    }
}