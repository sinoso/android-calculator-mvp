package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator,
    private var expression: Expression
) : MainContract.Presenter {

    override fun addOperatorToExpression(operator: Operator) {
        expression += operator
        showExpression()
    }

    override fun addOperandToExpression(operand: Int) {
        expression += operand
        showExpression()
    }

    override fun removeLast() {
        expression = expression.removeLast()
        showExpression()
    }

    override fun proceedCalculation() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpression()
            return
        }
        expression = Expression.EMPTY + result
        showExpression()
    }

    private fun showExpression() {
        view.showExpression(expression.toString())
    }
}