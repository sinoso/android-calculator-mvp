package edu.nextstep.camp.calculator.domain

class CalculationMemory(initialRecords: List<Record> = emptyList()) {
    private val _records = initialRecords.toMutableList()
    val records: List<Record>
        get() = _records.toList()

    constructor(vararg initialRecords: Record) : this(initialRecords.toList())

    fun addRecord(expression: Expression, result: Int) {
        Record(expression, result).also { _records.add(it) }
    }

    data class Record(
        val expression: Expression,
        val result: Int
    )
}
