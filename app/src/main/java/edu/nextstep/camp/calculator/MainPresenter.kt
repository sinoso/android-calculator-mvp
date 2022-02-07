package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun addToNumber(number: Int) {
        expression += number
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
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }
}