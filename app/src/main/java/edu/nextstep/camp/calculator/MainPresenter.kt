package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var expression = Expression.EMPTY

    override fun addOperand(rawOperand: String) {
        expression = expression.addOperand(rawOperand)
        view.refreshExpressionView(expression.rawExpression)
    }

    override fun addOperator(rawOperator: String) {
        expression = expression.addOperator(rawOperator)
        view.refreshExpressionView(expression.rawExpression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.refreshExpressionView(expression.rawExpression)
    }

    override fun calculate() {
        try {
            view.refreshExpressionView(expression.getResult().toString())
        } catch (e: IllegalArgumentException) {
            view.showErrorToast()
        }
    }
}