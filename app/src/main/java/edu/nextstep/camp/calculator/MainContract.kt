package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(string: String)
        fun showIncompleteExpression()
    }

    interface Presenter {
        fun addOperatorToExpression(operator: Operator)
        fun addOperandToExpression(operand: Int)
        fun removeLast()
        fun proceedCalculation()
    }
}