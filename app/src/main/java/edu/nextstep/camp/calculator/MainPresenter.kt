package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator()
) : MainContract.Presenter {


    override fun addOperator(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun addOperand(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun removeLastExpression() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showErrorMsg()
        } else {
            view.showExpression(Expression.EMPTY + result)
        }
    }
}