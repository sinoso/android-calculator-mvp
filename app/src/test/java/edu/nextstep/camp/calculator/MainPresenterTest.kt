package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Histories
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View
    private lateinit var histories: Histories

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        histories = Histories()
        presenter = MainPresenter(view, histories)
    }


    @Test
    @DisplayName("숫자1이 입력되면 숫자1이 보여야 한다")
    internal fun test1() {
        //give
        val expected = Expression(listOf(1))
        //   every { view.showExpression(expected) } just Runs

        //when
        presenter.addToExpression(1)

        //then
        verify { view.showExpression(expected) }


    }

    @Test
    @DisplayName("1수식에 +가 입력되면 1 +가 보여야 한다")
    internal fun test2() {
        //give
        val expected = Expression(listOf(1, Operator.Plus))
        /*  every { view.showExpression(Expression(listOf(1))) } answers { nothing }
          every { view.showExpression(expected) } answers { nothing }*/
        presenter.addToExpression(1)

        //when
        presenter.addToExpression(Operator.Plus)

        //then
        verify { view.showExpression(expected) }


    }

    @Test
    @DisplayName("1 + 2 수식이 있을 때,계산이 실행되면 3을 보여줘야 한다")
    internal fun test3() {
        //given

        //  every { view.showExpression(any()) } answers { nothing }
        //   every { view.showResult(3) } answers { nothing }
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)

        //when
        presenter.calculate()

        //then
        verify { view.showResult(3) }
    }

    @Test
    @DisplayName("1 + 2 일 때, 마지막을 제거하면 수식은 1 + 이어야 한다")
    internal fun test4() {
        //give
        val expected = Expression(listOf(1, Operator.Plus, 2))
        //   every { view.showExpression(Expression(listOf(1))) } answers {nothing}
        //    every { view.showExpression(Expression(listOf(1,Operator.Plus))) } answers {nothing}
        //    every { view.showExpression(expected) } answers {nothing}
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)
        //when
        presenter.removeLast()

        //then
        verify { view.showExpression(expected) }


    }

}