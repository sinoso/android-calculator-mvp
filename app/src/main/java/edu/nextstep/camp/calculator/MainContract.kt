package edu.nextstep.camp.calculator

import edu.nextstep.domain.CalculateHistoryItems
import edu.nextstep.domain.Expression
import edu.nextstep.domain.Operator

interface MainContract {
    interface View {
        fun refreshExpression(expression: Expression)
        fun showToastIncompleteExpression()
        fun toggleHistoryView()
        fun refreshHistory(historyItems: CalculateHistoryItems)
    }

    interface Presenter {
        fun addOperand(operand: String)
        fun addOperator(operator: Operator)
        fun removeLast()
        fun calculate()
        fun addHistory(result: Int)
    }
}