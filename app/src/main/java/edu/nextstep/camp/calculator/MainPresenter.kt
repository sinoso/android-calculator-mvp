package edu.nextstep.camp.calculator

import com.joseph.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression.EMPTY,
    private val calculateRecorder: CalculateRecorder = CalculateRecorder()
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
            addCalculateRecord(CalculateRecord(expression, result))
            expression = Expression.EMPTY + result
            view.displayExpression(expression)
        }
    }

    override fun addCalculateRecord(record: CalculateRecord) {
        val records = calculateRecorder.recordCalculate(record)
        view.refreshCalculateRecords(records)
    }

    override fun toggleCalculateResults() {
        view.toggleCalculateResults()
    }
}