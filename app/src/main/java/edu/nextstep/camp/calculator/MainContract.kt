package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {

        fun refreshCount(text: String)
        fun showToast(message: Int)
    }

    interface Presenter {

        fun inputNumber(value: Int)
        fun inputOperator(operator: Operator)
        fun removeLast()
        fun calculate()
    }
}