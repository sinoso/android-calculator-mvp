package edu.nextstep.camp.calculator

import com.joseph.domain.Calculator
import com.joseph.domain.Expression
import com.joseph.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression.EMPTY
): MainContract.Presenter {

    private val calculator = Calculator()

    override fun addExpression(number: Int) {
        expression += number
        view.displayExpression(expression)
    }

    override fun addExpression(operator: Operator) {
        expression += operator
        view.displayExpression(expression)
    }

    override fun removeAtLastExpression() {
        expression = expression.removeLast()
        view.displayExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpressionToast()
        } else {
            expression = Expression.EMPTY + result
            view.displayExpression(expression)
        }
    }
}