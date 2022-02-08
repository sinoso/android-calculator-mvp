package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY
): MainContract.Presenter {

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun calculate() {
        try {
            val calculatedValue = calculator.calculate(expression.toString())
            expression = Expression.EMPTY + (calculatedValue ?: throw IllegalArgumentException("유효하지 않은 수식입니다.") )
            view.showExpression(expression)
        }
        catch (e: IllegalArgumentException) {
            view.onError(e)
        }
    }

    override fun delete() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

}