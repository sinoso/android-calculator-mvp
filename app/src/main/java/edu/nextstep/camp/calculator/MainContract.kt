package edu.nextstep.camp.calculator

import androidx.annotation.StringRes
import edu.nextstep.camp.calculator.base.BaseView
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.model.CalculatorMemoryItem

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showExpression(expression: Expression)

        fun hideExpression()

        fun showToast(@StringRes stringId: Int)

        fun showMemoryList()

        fun hideMemoryList()

        fun notifyMemoryList(items: List<CalculatorMemoryItem>)

        fun getMemoryListVisible(): Boolean
    }

    interface Presenter {
        fun addToExpression(operand: Int)

        fun addToExpression(operator: Operator)

        fun removeLastToExpression()

        fun calculateToExpression()

        fun checkMemoryListVisible()

        fun updateMemoryList()
    }
}