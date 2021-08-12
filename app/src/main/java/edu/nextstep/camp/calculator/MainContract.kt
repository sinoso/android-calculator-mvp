package edu.nextstep.camp.calculator

import com.joseph.domain.Expression
import com.joseph.domain.Operator

interface MainContract {
    interface View {
        fun displayExpression(expression: Expression)
        fun showIncompleteExpressionToast()
    }

    interface Presenter {
        fun addExpression(number: Int)
        fun addExpression(operator: Operator)
        fun removeAtLastExpression()
        fun calculate()
    }
}