package edu.nextstep.camp.calculator

import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @DisplayName("숫자 1이 입력되면 1이 보여아 한다")
    @Test
    fun whenClick1_thenDisplay1() {
        // when
        presenter.addOperand("1")

        // then
        verify { view.refreshExpressionView("1") }
    }

    @DisplayName("수식 2 + 3이 입력되면 2 + 3이 보여아 한다")
    @Test
    fun whenClickExpression_thenDisplayExpression() {
        // when
        presenter.addOperand("2")
        presenter.addOperator("+")
        presenter.addOperand("3")

        // then
        verify { view.refreshExpressionView("2 + 3") }
    }

    @DisplayName("입력된 수식이 2 + 3 일 때 지우기 버튼을 누르면 2 + 이 된다")
    @Test
    fun givenDisplayExpression_whenClickDelete_thenDisplayExpression2() {
        // given
        presenter.addOperand("2")
        presenter.addOperator("+")
        presenter.addOperand("3")

        // when
        presenter.removeLast()

        // then
        verify { view.refreshExpressionView("2 +") }
    }

    @DisplayName("입력된 수식이 2 + 3 * 5 일 때 = 버튼을 누르면 25.0 이 보여야 한다")
    @Test
    fun givenDisplayExpression_whenClickEquals_thenDisplayResult() {
        // given
        presenter.addOperand("2")
        presenter.addOperator("+")
        presenter.addOperand("3")
        presenter.addOperator("×")
        presenter.addOperand("5")

        // when
        presenter.calculate()

        // then
        verify { view.refreshExpressionView("25.0") }
    }

    @DisplayName("사칙연산이 아닌 기호가 입력된 경우 = 버튼을 누르면 토스트 팝업이 보여야 한다")
    @Test
    fun givenDisplayExpression_whenClickEquals_thenDisplayResult2() {
        // given
        presenter.addOperand("2")
        presenter.addOperator("^")
        presenter.addOperand("3")

        // when
        presenter.calculate()

        // then
        verify { view.showErrorToast() }
    }
}