package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Memory

interface MainContract {
    interface View {
        fun showExpression(expression: String)
        fun showExpressionError()
        fun memorizeHistory(memory: Memory)
        fun showHistory()
        fun showCalculate()
    }

    interface Presenter {
        fun addToNumber(number: String)
        fun addToPlus()
        fun addToMinus()
        fun addToMultiply()
        fun addToDivide()
        fun deleteLastInput()
        fun calculate()
        fun isHistoryVisible(isVisible: Boolean)
    }
}