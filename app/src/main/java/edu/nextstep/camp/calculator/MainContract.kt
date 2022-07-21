package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View {
        fun showExpression(expression: String)
        fun showIncomplete()
    }

    interface Presenter {
        fun enterNumber(number: Int)
        fun enterOperator(operator: Operator)
        fun calculate()
        fun removeLast()
    }
}
