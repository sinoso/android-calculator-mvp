package edu.nextstep.camp.calculator

interface MainContract {
    interface View {
        fun showExpression(expression: String?)
    }

    interface Presenter {
        fun calculate(statement: String)
        fun appendOperand(statement: String, operand: String)
        fun appendOperator(statement: String, operator: String)
        fun deleteLastElement(statement: String)
    }
}