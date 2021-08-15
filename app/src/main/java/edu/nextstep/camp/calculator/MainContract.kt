package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: Expression)
        fun showErrorMsg()
    }

    interface Presenter {
        fun addOperator(operator: Operator)
        fun addOperand(operand: Int)
        fun removeLastExpression()
        fun calculate()
    }
}