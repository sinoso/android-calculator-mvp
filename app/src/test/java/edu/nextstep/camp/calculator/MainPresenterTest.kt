package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View
    private lateinit var expression: Expression

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        expression = Expression.EMPTY
        presenter = MainPresenter(view)
    }


    @Test
    @DisplayName("숫자1이 입력되면 숫자1이 보여야 한다")
    internal fun test1() {
        //give

        //when
        presenter.inputNumber(1)

        //then
        verify { view.refreshCount("1") }


    }

    @Test
    @DisplayName("+가 입력되면 +가 보여야 한다")
    internal fun test2() {
        //give

        //when
        presenter.inputOperator(Operator.Plus)

        //then
        verify { view.refreshCount("+") }


    }

    @Test
    @DisplayName("13 + 일 때, 마지막을 제거하면 수식은 13 이어야 한다")
    internal fun test3() {
        //give
        presenter.inputNumber(1)
        presenter.inputNumber(3)
        presenter.inputOperator(Operator.Plus)

        //when
        presenter.removeLast()

        //then
        verify { view.refreshCount("13") }


    }


    @Test
    @DisplayName("2 + 3이 입력 되었을때 = 을 누르면 5가 보여아 한다.")
    internal fun test4() {
        //give
        presenter.inputNumber(2)
        presenter.inputOperator(Operator.Plus)
        presenter.inputNumber(3)

        //when
        presenter.calculate()

        //then
        verify { view.refreshCount("5") }


    }
}