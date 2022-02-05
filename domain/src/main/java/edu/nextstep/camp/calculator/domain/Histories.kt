package edu.nextstep.camp.calculator.domain

import java.lang.StringBuilder

data class Histories(
    val records: List<Record> = emptyList()
) {
    operator fun plus(record: Record): Histories {
        return Histories(records + record)
    }

    override fun toString(): String {
        val historyStringBuilder = StringBuilder()
        records.forEach {
            historyStringBuilder.append("${it.expression}\n")
            historyStringBuilder.append("${it.result}\n")
        }
        return historyStringBuilder.toString()
    }

    companion object {
        val EMPTY = Histories()
    }
}
