package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator,
    private var expression: Expression,
    private var calculationResultStorage: CalculationResultStorage
) : MainContract.Presenter {

    override fun addOperatorToExpression(operator: Operator) {
        expression += operator
        showExpression()
    }

    override fun addOperandToExpression(operand: Int) {
        expression += operand
        showExpression()
    }

    override fun removeLastFromExpression() {
        expression = expression.removeLast()
        showExpression()
    }

    override fun proceedCalculation() {
        val result = calculator.calculate(expression.toString())
        if (result == null || expression.toString() == result.toString()) {
            view.showToastForIncompleteExpressionInputted()
            return
        }
        calculationResultStorage += CalculationResult(expression, result)
        expression = Expression.EMPTY + result
        showExpression()
    }

    override fun requestChangeCalculateResults() {
        view.changeCalculateResults(calculationResultStorage.getResultsAsList())
    }

    private fun showExpression() {
        view.showExpression(expression.toString())
    }
}