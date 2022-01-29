package edu.nextstep.camp.calculator

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @Test
    fun `수식 1 을 입력하면 1이 보여야한다`() {
        // WHEN
        presenter.appendOperand("", "1")

        // THEN
        verify { view.showExpression("1") }
    }

    @Test
    fun `수식 1 + 을 입력하면 1 + 이 보여야한다`() {
        // WHEN
        presenter.appendOperator("1", "+")

        // THEN
        verify { view.showExpression("1 +") }
    }

    @Test
    fun `수식 1 + 2 - 1 을 입력하면 1이 보여야한다`() {
        // WHEN
        presenter.calculate("1 + 2 - 1")

        // THEN
        verify { view.showExpression("2") }
    }

    @Test
    fun `수식 1 + 2 - 1 에서 요소를 제거하면 1 + 2 - 가 보여야한다`() {
        // WHEN
        presenter.deleteLastElement("1 + 2 - 1")

        // THEN
        verify { view.showExpression("1 + 2 -") }
    }
}
