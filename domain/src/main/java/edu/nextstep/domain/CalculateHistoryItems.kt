package edu.nextstep.domain

class CalculateHistoryItems(private val histories: List<CalculateHistoryItem> = emptyList()) {
    operator fun plus(historyItem: CalculateHistoryItem): CalculateHistoryItems {
        return CalculateHistoryItems(histories + historyItem)
    }

    fun getItems(): List<CalculateHistoryItem> {
        return histories
    }
}