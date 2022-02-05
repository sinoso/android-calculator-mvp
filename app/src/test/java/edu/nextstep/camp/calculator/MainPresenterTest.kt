package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.*
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator())
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator(), Expression(listOf(1)))
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.addToExpression(Operator.Plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 +")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `지우기가 입력되면 마지막에 입력된 수식이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator(), Expression(listOf(1, Operator.Plus)))
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.removeLatest()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `등호가 입력되면 수식이 계산되고 계산된 수식을 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator(), Expression(listOf(1, Operator.Plus, 2)))
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("3")
        verify(exactly = 1) { view.showExpression(actual) }
    }

    @Test
    fun `히스토리가 보이도록 디스플레이를 바꾸면 히스토리가 보여야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator())

        // when
        presenter.changeDisplay(true)

        // then
        verify(exactly = 1) { view.showCalculateDisplay() }
    }

    @Test
    fun `히스토리가 보이지 않도록 디스플레이를 바꾸면 수식 화면이 보여야 한다`() {
        // given
        presenter = MainPresenter(view, Calculator())

        // when
        presenter.changeDisplay(false)

        // then
        verify(exactly = 1) { view.showHistoryDisplay() }
    }
}
