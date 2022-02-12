package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression.EMPTY
    private var memory = Memory.EMPTY
    private var mode: Mode = expression

    override fun addToExpression(operand: Int) {
        expression += operand
        updateExpression()
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        updateExpression()
    }

    override fun removeLastInExpression() {
        expression = expression.removeLast()
        updateExpression()
    }

    override fun evaluateByExpression() {
        val result = calculator.calculate(expression.toString()) ?: run {
            view.showError()
            return
        }

        memory += Memory.Item(expression.toString(), result)
        expression = Expression.EMPTY + result

        updateExpression()
    }

    override fun toggleMode() {
        when (mode) {
            is Expression -> updateMemory()
            is Memory -> updateExpression()
        }
    }

    private fun updateMemory() {
        mode = memory
        view.showMemory(memory.items)
    }

    private fun updateExpression() {
        mode = expression
        view.showExpression(expression.toString())
    }
}