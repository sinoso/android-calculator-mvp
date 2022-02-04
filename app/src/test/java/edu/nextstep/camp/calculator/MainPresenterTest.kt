package edu.nextstep.camp.calculator

import edu.nextstep.domain.Expression
import edu.nextstep.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

internal class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View
    private lateinit var expression: Expression

    @Before
    fun setUp() {
        view = mockk(relaxUnitFun = true)
        presenter = MainPresenter(view)
        expression = Expression.EMPTY
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 없을때, 피연산자 3,2,1을 연속해서 추가하면 텍스트뷰에 '321' 이 출력되어야 한다`() {
        // when : 피연산자 3,2,1 추가
        presenter.addOperand("3")
        presenter.addOperand("2")
        presenter.addOperand("1")

        // then : 321 출력
        expression += 321
        verify { view.refreshExpression(expression) }
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 없을때, 연산자를 추가해도 아무런 변화가 없어야 한다`() {
        // when : 연산자 추가
        presenter.addOperator(Operator.Divide)

        // then : 변화 없음
        expression = Expression.EMPTY
        verify { view.refreshExpression(expression) }
    }

    @Test
    fun `계산기 텍스트뷰에 피연산자가 있을때, 연산자를 누르면 피연산자와 연산자가 화면에 이어서 출력되어야 한다`() {
        // given : 수식 '6'이 존재할때
        presenter.addOperand("6")

        // when : 연산자 '+'를 누르면
        presenter.addOperator(Operator.Plus)

        // then : '6+'이 출력되어야 한다.
        expression = Expression.EMPTY + 6 + Operator.Plus
        verify { view.refreshExpression(expression) }
    }

    @Test
    fun `계산기 텍스트뷰 수식의 마지막이 연산자로 있을때, 연산자를 누르면 연산자가 변경되어야 한다`() {
        // given : 수식이 '9+'로 존재할때
        presenter.addOperand("9")
        presenter.addOperator(Operator.Plus)

        // when : 연산자 '/'를 누르면
        presenter.addOperator(Operator.Divide)

        // then : 연산자 '9/'이 출력되어야 한다.
        expression = Expression.EMPTY + 9 + Operator.Divide
        verify { view.refreshExpression(expression) }
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 있을때, 지우기 버튼을 수식의 마지막 문자가 지워진다`() {
        // given : 수식 '20/10'이 있을때
        presenter.addOperand("20")
        presenter.addOperator(Operator.Divide)
        presenter.addOperand("10")

        // when : 지우기 버튼을 누르면
        presenter.removeLast()

        // then : '20/' 이 출력되어야 한다.
        expression = Expression.EMPTY + 20 + Operator.Divide
        verify { view.refreshExpression(expression) }
    }

    @Test
    fun `계산기 텍스트뷰에 완전한 수식이 있을때, = 버튼을 누르면 수식이 계산되어야 한다`() {
        // given : 완전한 수식 '3+10'이 있을때
        presenter.addOperand("3")
        presenter.addOperator(Operator.Plus)
        presenter.addOperand("10")

        // when : '=' 버튼을 누르면
        presenter.calculate()

        // then : 계산 결과 '13'이 출력 되어야 한다.
        expression += 13
        verify { view.refreshExpression(expression) }
    }

    @Test
    fun `계산기 텍스트뷰에 불완전한 수식이 있을때, = 버튼을 누르면 토스트가 출력되어야 한다`() {
        // given : 불완전한 수식 '12*'이 있을때
        presenter.addOperand("12")
        presenter.addOperator(Operator.Multiply)

        // when : '=' 버튼을 누르면
        presenter.calculate()

        // then : 토스트가 출력 되어야 한다.
        verify { view.showToastIncompleteExpression() }
    }
}