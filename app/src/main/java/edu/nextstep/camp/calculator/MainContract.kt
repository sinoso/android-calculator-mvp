package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.CalculationHistory
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: Expression)
        fun showErrorMsg()
        fun showHistories(histories: List<CalculationHistory>)
        fun hideHistories()
    }

    interface Presenter {
        fun addOperator(operator: Operator)
        fun addOperand(operand: Int)
        fun removeLastExpression()
        fun calculate()
        fun loadHistory(isShowingHistories: Boolean)
    }
}