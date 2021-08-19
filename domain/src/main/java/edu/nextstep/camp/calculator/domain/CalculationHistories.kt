package edu.nextstep.camp.calculator.domain

class CalculationHistories(
    histories: List<CalculationHistory> = listOf()
) {
    private val _list: MutableList<CalculationHistory> = histories.toMutableList()
    val list: List<CalculationHistory>
        get() = _list.toList()

    fun addHistory(historyHistory: CalculationHistory): CalculationHistories {
        return CalculationHistories(_list + historyHistory)
    }
}