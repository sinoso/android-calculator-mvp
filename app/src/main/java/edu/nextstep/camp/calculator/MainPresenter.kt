package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private var memory: Memory = Memory.EMPTY
) : MainContract.Presenter {

    override fun addToNumber(number: String) {
        expression += number.toInt()
        view.showExpression(expression.toString())
    }

    override fun addToPlus() {
        expression += Operator.Plus
        view.showExpression(expression.toString())
    }

    override fun addToMinus() {
        expression += Operator.Minus
        view.showExpression(expression.toString())
    }

    override fun addToMultiply() {
        expression += Operator.Multiply
        view.showExpression(expression.toString())
    }

    override fun addToDivide() {
        expression += Operator.Divide
        view.showExpression(expression.toString())
    }

    override fun deleteLastInput() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showExpressionError()
            return
        }
        memory += History(expression.toString(), result.toString())
        view.memorizeHistory(memory)

        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }

    override fun isHistoryVisible(isVisible: Boolean) {
        when (isVisible) {
            true -> view.showHistory()
            false -> view.showCalculate()
        }
    }
}