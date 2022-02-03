package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun inputNumber(number: Int) {
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
        expression += operator
        showExpression()
    }

    override fun deleteLast() {
        expression = expression.removeLast()
        showExpression()
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showExpressionError()
        } else {
            showCalculated(result)
        }
    }

    private fun showCalculated(answer: Int) {
        expression = Expression.EMPTY + answer
        showExpression()
    }

    private fun showExpression() {
        view.showExpression(expression.toString())
    }
}
