package edu.nextstep.camp.calculator

import edu.nextstep.domain.Calculator
import edu.nextstep.domain.Expression
import edu.nextstep.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression.EMPTY
) : MainContract.Presenter {

    private val calculator = Calculator()

    override fun addOperand(operand: String) {
        expression += operand.toInt()
        view.refreshExpression(expression)
    }

    override fun addOperator(operator: Operator) {
        expression += operator
        view.refreshExpression(expression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.refreshExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.showToastIncompleteExpression()
            return
        }
        expression = Expression.EMPTY + result
        view.refreshExpression(expression)
    }
}