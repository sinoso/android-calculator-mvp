package edu.nextstep.camp.calculator.domain

data class Memory(
    val histories: List<History> = emptyList()
) {
    val historyString: String
        get() = buildString {
            histories.forEach {
                append(it.Expression)
                append(it.resultValue)
            }
        }

    operator fun plus(history: History): Memory {
        return Memory(histories + history)
    }

    companion object {
        val EMPTY = Memory()
    }
}
