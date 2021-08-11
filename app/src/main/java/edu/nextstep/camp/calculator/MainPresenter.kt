package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression.EMPTY,
) : MainContract.Presenter {

    override fun addToExpression(operand: Int) {
        expression += operand
        view.refreshExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        if (expression == Expression.EMPTY) return
        expression += operator
        view.refreshExpression(expression)
    }

    override fun clear() {
        if (expression == Expression.EMPTY) return
        expression = Expression.EMPTY
        view.refreshExpression(expression)
    }

    override fun removeLastOfExpression() {
        expression = expression.removeLast()
        view.refreshExpression(expression)
    }

    override fun calculateExpression() {
        val result = Calculator.calculate(expression.toString())
        if (result == null) {
            view.notifyIncompleteExpression()
            return
        }
        expression = Expression(result)
        view.refreshExpression(expression)
    }
}
