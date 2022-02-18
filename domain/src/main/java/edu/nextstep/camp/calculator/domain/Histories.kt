package edu.nextstep.camp.calculator.domain

class Histories {
    private val memories = mutableListOf<HistoryData>()

    val items: List<HistoryData> get() = memories

    fun add(expression: Expression, result: Int) {
        memories.add(HistoryData(expression.toString(), result.toString()))
    }

    override fun toString(): String {
        return items.joinToString(",", "[", "]")
    }
}