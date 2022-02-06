package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorPresenter(
    private val view: CalculatorContract.View,
) : CalculatorContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY
    private val isEmptyExpression
        get() = expression == Expression.EMPTY

    constructor(view: CalculatorContract.View, expression: Expression) : this(view) {
        this.expression = expression
    }

    override fun addExpressionElement(element: Int) {
        expression += element
        view.refreshExpression(expression)
    }

    override fun addExpressionElement(element: Operator) {
        if (isEmptyExpression) return
        expression += element
        view.refreshExpression(expression)
    }

    override fun removeLastExpressionElement() {
        if (isEmptyExpression) return
        expression = expression.removeLast()
        view.refreshExpression(expression)
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.notifyInCompleteExpression()
            return
        }
        expression = Expression.EMPTY + result
        view.refreshExpression(expression)
    }
}
