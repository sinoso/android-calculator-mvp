package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Histories
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression.EMPTY,
    private var histories: Histories = Histories(),
    private var calculatorDisabled: Boolean = false
) : MainContract.Presenter {
    constructor(view: MainContract.View, expression: String): this(view, toExpression(expression))

    override fun inputNumber(number: Int) {
        if (calculatorDisabled) return
        expression += number
        showExpression()
    }

    override fun inputPlus() {
        inputOperator(Operator.Plus)
    }

    override fun inputMinus() {
        inputOperator(Operator.Minus)
    }

    override fun inputMultiply() {
        inputOperator(Operator.Multiply)
    }

    override fun inputDivide() {
        inputOperator(Operator.Divide)
    }

    private fun inputOperator(operator: Operator) {
        if (calculatorDisabled) return
        expression += operator
        showExpression()
    }

    override fun deleteLast() {
        if (calculatorDisabled) return
        expression = expression.removeLast()
        showExpression()
    }

    override fun calculate() {
        if (calculatorDisabled) return
        val rawExpression = expression.toString()
        val result = calculator.calculate(rawExpression)
        if (result == null) {
            view.showExpressionError()
        } else {
            histories += History(rawExpression, result)
            view.notifyHistories(histories.toModel())
            expression = Expression.EMPTY + result
            showExpression()
        }
    }

    override fun toggleCalculator() {
        calculatorDisabled = !calculatorDisabled
        if (calculatorDisabled) {
            view.showHistory()
        } else {
            view.hideHistory()
        }
    }

    private fun showExpression() {
        view.showExpression(expression.toString())
    }

    private fun History.toModel(): HistoryModel = HistoryModel(rawExpression, "= $result")

    private fun Histories.toModel(): List<HistoryModel> = toList().map { it.toModel() }

    companion object {
        private val calculator: Calculator = Calculator()

        private fun toExpression(rawExpression: String): Expression =
            Expression(rawExpression.trim().split(" ").map { it.toExpressionValue() })

        private fun String.toExpressionValue(): Any  {
            val operator = Operator.of(this)
            if (operator != null) {
                return operator
            }
            return toInt()
        }
    }
}
