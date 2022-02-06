package edu.nextstep.camp.calculator

sealed class CalculatorViewType

object ExpressionView : CalculatorViewType()

object MemoryView : CalculatorViewType()

fun CalculatorViewType.toggle(): CalculatorViewType {
    return when (this) {
        is ExpressionView -> MemoryView
        is MemoryView -> ExpressionView
    }
}