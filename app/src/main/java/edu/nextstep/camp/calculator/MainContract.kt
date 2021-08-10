package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View {

        fun showExpression(expression: Expression)

        fun showError()

        fun showHistories(histories: List<History>)

        fun hideHistories()
    }

    interface Presenter {
        val expression: Expression

        val histories: List<History>

        fun formatExpression(number: Int)

        fun formatExpression(operator: Operator)

        fun deleteExpression()

        fun calculate()
    }

}
