package com.github.dodobest.domain

class Calculator {
    fun calculate(inputArray: List<String>): Double {
        var sum: Double = inputArray[0].toDouble()

        for (idx in OPERATION_START_INDEX until inputArray.size step OPERATION_INTERVAL_AT_INDEX) {
            val operation = inputArray[idx]
            val num = inputArray[idx+1]
            sum = Operation.convertToOperation(operation).calculate(sum, num.toDouble())
        }

        return sum
    }

    companion object {
        const val OPERATION_START_INDEX = 1
        const val OPERATION_INTERVAL_AT_INDEX = 2
    }
}