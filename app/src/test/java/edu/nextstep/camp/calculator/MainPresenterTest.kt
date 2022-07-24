package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MainPresenterTest {
    private lateinit var mainPresenter: MainPresenter
    lateinit var view: MainContract.View

    @BeforeEach
    fun initPresenter() {
        view = mockk(relaxUnitFun = true)
        mainPresenter = MainPresenter(view = view, Calculator(), Expression.EMPTY)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0])
    fun `수식이 빈상태에서 피연산자가 추가 되면 해당 피연산자가 보인다`(operand: Int) {
        // when
        mainPresenter.addOperandToExpression(operand)
        // then
        verify { view.showExpression(operand.toString()) }
    }

    @Test
    fun `'8' 수식이 있을 때, 9를 입력하면 89로 바뀌어야 한다`() {
        // given
        mainPresenter.addOperandToExpression(8)
        // when
        mainPresenter.addOperandToExpression(9)
        // then
        verify { view.showExpression("89") }
    }

    @Test
    fun `빈 수식일 때, 연산자를 추가하면, 수식이 변하지 않는다`() {
        // when
        mainPresenter.addOperatorToExpression(Operator.Plus)
        // then
        verify { view.showExpression("") }
    }

    @Test
    fun `'1' 수식이 있을 때, + 연산자를 추가하면 '1 +'가 view에 보인다`() {
        // given
        mainPresenter.addOperandToExpression(1)
        // when
        mainPresenter.addOperatorToExpression(Operator.Plus)
        // then
        verify { view.showExpression("1 +") }
    }

    @Test
    fun `'8 +' 수식이 있을 때, - 연산자를 입력시 '8 -'로 변경 된다`() {
        // given
        mainPresenter.addOperandToExpression(8)
        mainPresenter.addOperatorToExpression(Operator.Plus)
        // when
        mainPresenter.addOperatorToExpression(Operator.Minus)
        // then
        verify { view.showExpression("8 -") }
    }

    @Test
    fun `'32 + 1' 수식이 있을 때, 마지막제거 요청시 피연산자'1'이 제거된 '32 +'로 변경 된다`() {
        // given
        mainPresenter.addOperandToExpression(3)
        mainPresenter.addOperandToExpression(2)
        mainPresenter.addOperatorToExpression(Operator.Plus)
        mainPresenter.addOperandToExpression(1)
        // when
        mainPresenter.removeLast()
        // then
        verify { view.showExpression("32 +") }
    }

    @Test
    fun `'32 +' 수식이 있을 때, 마지막제거 요청시 연산자'+'가 제거된 '32'로 변경 된다`() {
        // given
        mainPresenter.addOperandToExpression(3)
        mainPresenter.addOperandToExpression(2)
        mainPresenter.addOperatorToExpression(Operator.Plus)
        // when
        mainPresenter.removeLast()
        // then
        verify { view.showExpression("32") }
    }

    @Test
    fun `'32' 수식이 있을 때, 마지막제거 요청시 피연산자'2'가 제거된 '3'으로 변경 된다`() {
        // given
        mainPresenter.addOperandToExpression(3)
        mainPresenter.addOperandToExpression(2)
        // when
        mainPresenter.removeLast()
        // then
        verify { view.showExpression("3") }
    }

    @Test
    fun `'3' 수식이 있을 때, 마지막제거 요청시 피연산자'3'가 제거된 ''으로 변경 된다`() {
        // given
        mainPresenter.addOperandToExpression(3)
        // when
        mainPresenter.removeLast()
        // then
        verify { view.showExpression("") }
    }

    @Test
    fun `빈 수식일 때, 마지막을 제거요청시 빈 수식이어야 한다`() {
        // given
        // when
        mainPresenter.removeLast()
        // then
        verify { view.showExpression("") }
    }
}