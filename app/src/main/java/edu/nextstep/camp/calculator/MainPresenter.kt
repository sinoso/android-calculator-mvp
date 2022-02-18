package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private val histories: Histories
) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression: Expression = Expression.EMPTY

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpressionError()
            return
        }
        histories.add(expression,result)
        expression = Expression.EMPTY + result
        view.showResult(result)
    }

    override fun setHistoryData() {
        view.showHistory(histories)
    }

}