package edu.nextstep.camp.calculator.domain

enum class Operator(
    private val operator: String?,
    private val operatorAction: OperatorAction
) : OperatorAction by operatorAction {
    PLUS("+", { operand1, operand2 -> operand1 + operand2 }),
    MINUS("-", { operand1, operand2 -> operand1 - operand2 }),
    MULTIPLY("×", { operand1, operand2 -> operand1 * operand2 }),
    DIVIDE("÷", { operand1, operand2 -> operand1 / operand2 }),
    NONE(null, { _, operand2 -> operand2 });

    companion object {
        fun getOperator(operator: String): Operator =
            values().find { it.operator == operator }
                ?: throw IllegalArgumentException("exception operator")
    }
}