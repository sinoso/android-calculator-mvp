package edu.nextstep.camp.calculator

import edu.nextstep.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator,
    private var expression: Expression = Expression.EMPTY,
    private var calculateHistoryItems: CalculateHistoryItems = CalculateHistoryItems()
) : MainContract.Presenter {

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
        addHistory(result)
        expression = Expression.EMPTY + result
        view.refreshExpression(expression)
    }

    override fun addHistory(result: Int) {
        calculateHistoryItems += CalculateHistoryItem(expression, Expression.EMPTY + result)
        view.refreshHistory(calculateHistoryItems)
    }
}