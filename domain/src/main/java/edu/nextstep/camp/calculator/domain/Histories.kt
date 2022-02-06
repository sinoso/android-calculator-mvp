package edu.nextstep.camp.calculator.domain

class Histories(private val histories: List<History> = emptyList()) {
    operator fun plus(history: History): Histories = Histories(histories + listOf(history))

    fun toList(): List<History> = histories.toList()
}
