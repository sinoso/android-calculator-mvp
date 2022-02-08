package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY
) : MainContract.Presenter {
    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun evaluate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpressionToast()
            return
        }
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }
}
