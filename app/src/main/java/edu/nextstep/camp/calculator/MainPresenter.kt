package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private var expression = Expression.EMPTY

    private val calculator = Calculator()

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun removeLastToExpression() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculateToExpression() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showToast(R.string.incomplete_expression)
            return
        }
        expression = Expression.EMPTY + result
        view.showExpression(expression)
    }
}