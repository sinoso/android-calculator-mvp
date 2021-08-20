package com.joseph.domain

class CalculateRecords(
    val value: List<CalculateRecord> = emptyList()
) {

    operator fun plus(record: CalculateRecord): CalculateRecords {
        return CalculateRecords(value + record)
    }
}