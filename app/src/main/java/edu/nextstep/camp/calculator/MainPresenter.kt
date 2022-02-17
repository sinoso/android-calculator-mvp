package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private val calculateStorage: CalculateStorage = MemoryCalculateStorage()
): MainContract.Presenter {

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun displayCalculateHistory() {
        view.showCalculateHistory(
            calculateStorage.history.map { historyItem ->  getStringForDisplay(historyItem) }
        )
    }

    private fun getStringForDisplay(historyItem: HistoryItem): String {
        return "${historyItem.formula}\n= ${historyItem.result}"
    }

    override fun calculate() {
        val calculatedValue = calculator.calculate(expression.toString())
        if(calculatedValue == null) {
            view.onError(IllegalArgumentException("유효하지 않은 수식입니다."))
            return
        }
        val calculatedExpression = Expression(listOf(calculatedValue))

        calculateStorage.save(HistoryItem(expression, calculatedExpression))
        expression = calculatedExpression

        view.showExpression(expression)
    }

    override fun delete() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }
}