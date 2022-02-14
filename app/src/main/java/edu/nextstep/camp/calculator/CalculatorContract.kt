package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.CalculationMemory
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.common.BaseView

interface CalculatorContract {
    interface View : BaseView<Presenter> {
        fun refreshExpression(expression: Expression)
        fun notifyInCompleteExpression()
        fun showCalculationMemory()
        fun hideCalculationMemory()
        fun refreshCalculationMemory(records: List<CalculationMemory.Record>)
    }

    interface Presenter {
        fun addExpressionElement(element: Int)
        fun addExpressionElement(element: Operator)
        fun removeLastExpressionElement()
        fun calculateExpression()
        fun toggleCalculationMemory(isVisible: Boolean)
    }
}
