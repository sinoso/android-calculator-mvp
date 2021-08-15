package edu.nextstep.camp.domain

/**
 * Created By Malibin
 * on 8월 12, 2021
 */

class CalculatorMemory(
    initialHistories: List<CalculationHistory> = emptyList()
) {
    private val histories: MutableList<CalculationHistory> = initialHistories.toMutableList()

    fun record(expression: Expression, result: Int) {
        val history = CalculationHistory(expression, result)
        histories.add(history)
    }

    fun size(): Int = histories.size

    fun getHistories(): List<CalculationHistory> = histories.toList()
}
