package com.joseph.domain

class CalculateRecorder {

    private var records: CalculateRecords = CalculateRecords()

    fun recordCalculate(record: CalculateRecord): CalculateRecords {
        records += record
        return records
    }
}