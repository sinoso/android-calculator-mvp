package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.MemoryRepository

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var expression = Expression.EMPTY
    private var isShowMemoryVisible = false
    private var memoryRepository = MemoryRepository()

    override fun addOperand(rawOperand: String) {
        hideMemoryView()
        expression = expression.addOperand(rawOperand)
        view.refreshExpressionView(expression.rawExpression)
    }

    private fun hideMemoryView() {
        if (isShowMemoryVisible) {
            isShowMemoryVisible = false
            view.showMemoryView(isShowMemoryVisible)
        }
    }

    override fun addOperator(rawOperator: String) {
        hideMemoryView()
        expression = expression.addOperator(rawOperator)
        view.refreshExpressionView(expression.rawExpression)
    }

    override fun removeLast() {
        hideMemoryView()
        expression = expression.removeLast()
        view.refreshExpressionView(expression.rawExpression)
    }

    override fun calculate() {
        hideMemoryView()
        try {
            val result = expression.getResult().toString()
            view.refreshExpressionView(result)
            addMemory(expression.rawExpression, result)
        } catch (e: IllegalArgumentException) {
            view.showErrorToast()
        }
    }

    override fun toggleMemory() {
        isShowMemoryVisible = !isShowMemoryVisible
        view.showMemoryView(isShowMemoryVisible)
    }

    private fun addMemory(expression: String, result: String) {
        val memory = memoryRepository.addMemory(expression, result)
        view.addMemory(memory)
    }
}