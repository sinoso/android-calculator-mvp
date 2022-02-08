package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import java.lang.Exception

interface BaseView<T> {
    var presenter: T
}

interface MainContract {
    interface View: BaseView<Presenter> {
        fun showExpression(expression: Expression)
        fun onError(exception: Exception)
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun calculate()
        fun delete()
    }
}