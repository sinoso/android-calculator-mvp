package edu.nextstep.camp.calculator

import com.joseph.domain.CalculateRecord
import com.joseph.domain.CalculateRecords
import com.joseph.domain.Expression
import com.joseph.domain.Operator

interface MainContract {
    interface View {
        fun displayExpression(expression: Expression)
        fun showIncompleteExpressionToast()
        fun toggleCalculateResults()
        fun refreshCalculateRecords(records: CalculateRecords)
    }

    interface Presenter {
        fun addExpression(number: Int)
        fun addExpression(operator: Operator)
        fun removeAtLastExpression()
        fun calculate()
        fun toggleCalculateResults()
        fun addCalculateRecord(record: CalculateRecord)
    }
}