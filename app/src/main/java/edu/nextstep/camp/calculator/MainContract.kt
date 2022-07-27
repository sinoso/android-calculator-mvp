package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.CalculationResult
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(string: String)
        fun showToastForIncompleteExpressionInputted()
        fun changeCalculateResults(calculationResultList: List<CalculationResult>)
    }

    interface Presenter {
        fun addOperatorToExpression(operator: Operator)
        fun addOperandToExpression(operand: Int)
        fun removeLastFromExpression()
        fun proceedCalculation()
        fun requestChangeCalculateResults()
    }
}