package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View {
        val presenter: Presenter

        fun showExpression(expression: String)
        fun showIncompleteExpressionToast()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLatest()
        fun calculate()
    }
}
