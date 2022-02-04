package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Memory

interface MainContract {

    interface View {

        fun refreshExpressionView(expression: String)

        fun showErrorToast()

        fun showMemoryView(isVisible: Boolean)

        fun addMemory(memory: Memory)

    }

    interface Presenter {
        fun addOperand(rawOperand: String)

        fun addOperator(rawOperator: String)

        fun removeLast()

        fun calculate()

        fun toggleMemory()

    }

}