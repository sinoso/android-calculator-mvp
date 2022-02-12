package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.base.BaseView
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showExpression(expression: String)
        fun showMemory(items: List<Memory.Item>)
        fun showError()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLastInExpression()
        fun evaluateByExpression()
        fun toggleMode()
    }
}