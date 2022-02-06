package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private var expression = Expression.EMPTY
    private val calculator = Calculator()

    init {
        view.refreshCount(expression.toString())
    }

    override fun inputNumber(value: Int) {
        expression += value
        view.refreshCount(expression.toString())
    }

    override fun inputOperator(operator: Operator) {
        expression += operator
        view.refreshCount(expression.toString())
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.refreshCount(expression.toString())
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showToast(R.string.incomplete_expression)
            return
        }
        expression = Expression.EMPTY + result
        view.refreshCount(result.toString())
    }
}