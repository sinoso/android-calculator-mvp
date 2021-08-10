package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.Result

class MainPresenter(
    expression: Expression = Expression.EMPTY,
    histories: List<History> = listOf(),
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
) : MainContract.Presenter {

    private var _expression: Expression = expression
    override val expression: Expression
        get() = _expression

    private val _histories = histories.toMutableList()
    override val histories: List<History>
        get() = _histories.toList()

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
        saveHistory(expression = _expression, result = result)
        _expression = Expression.EMPTY + result
        view.showExpression(_expression)
    }

    private fun saveHistory(expression: Expression, result: Int?) {
        _histories.add(
            History(
                expression = expression,
                result = Result(value = result ?: 0)
            )
        )
    }
}
