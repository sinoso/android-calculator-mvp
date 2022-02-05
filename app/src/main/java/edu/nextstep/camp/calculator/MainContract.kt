package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Histories
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View {
        val presenter: Presenter

        fun showExpression(expression: String)
        fun showHistory(histories: Histories)
        fun showIncompleteExpressionToast()
        fun showHistoryDisplay()
        fun showCalculateDisplay()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLatest()
        fun calculate()
        fun changeDisplay(isHistoryDisplay: Boolean)
    }
}
