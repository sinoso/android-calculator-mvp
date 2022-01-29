package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private val expression = Expression()

    override fun calculate(statement: String) {
        val result = expression.calculatedValue(statement)
        view.showExpression(result)
    }

    override fun appendOperand(statement: String, operand: String) {
        val appendedStatement = expression.appendOperand(statement, operand)
        view.showExpression(appendedStatement)
    }

    override fun appendOperator(statement: String, operator: String) {
        val appendedStatement = expression.appendOperator(statement, operator)
        view.showExpression(appendedStatement)
    }

    override fun deleteLastElement(statement: String) {
        val deletedStatement = expression.deleteLastElement(statement)
        view.showExpression(deletedStatement)
    }
}