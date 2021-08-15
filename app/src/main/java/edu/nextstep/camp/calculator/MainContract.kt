package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.CalculationHistory
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

interface MainContract {
    interface View {
        fun refreshExpression(expression: Expression)

        fun notifyIncompleteExpression()

        fun refreshCalculationHistories(histories: List<CalculationHistory>)
    }

    interface Presenter {
        fun addToExpression(operand: Int)

        fun addToExpression(operator: Operator)

        fun clear()

        fun removeLastOfExpression()

        fun calculateExpression()
    }
}
