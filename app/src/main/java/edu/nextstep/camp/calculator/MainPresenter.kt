package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private var _expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator()
) : MainContract.Presenter {

    override val expression: Expression
        get() = _expression

    override fun formatExpression(number: Int) {
        _expression += number
        view.showExpression(_expression)
    }

    override fun formatExpression(operator: Operator) {
        _expression += operator
        view.showExpression(_expression)
    }

    override fun deleteExpression() {
        _expression = _expression.removeLast()
        view.showExpression(_expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showError()
            return
        }
        _expression = Expression.EMPTY + result
        view.showExpression(_expression)
    }
}
