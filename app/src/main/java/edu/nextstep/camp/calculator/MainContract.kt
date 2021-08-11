package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

interface MainContract {
    interface View {
        fun refreshExpression(expression: Expression)

        fun notifyIncompleteExpression()
    }

    interface Presenter {
        fun addToExpression(operand: Int)

        fun addToExpression(operator: Operator)

        fun clear()

        fun removeLastOfExpression()

        fun calculateExpression()
    }
}
