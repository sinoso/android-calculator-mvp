package com.github.dodobest.domain

class InputHandler {
    private val calculator = Calculator()
    private val expression = Expression()
    private val numAndSignArray: MutableList<String> = MutableList(0) { "" }
    private val arraySize: Int get() = numAndSignArray.size

    fun handleInputArithmetic(inputOperation: String) {
        if (arraySize == 0) return

        if (Operation.isOperation(numAndSignArray.last())) {
            numAndSignArray[arraySize - 1] = inputOperation
            return
        }

        numAndSignArray.add(inputOperation)
    }

    fun handleInputNum (inputNum: String) {
        if (arraySize > 0 && expression.isNum(numAndSignArray.last().toCharArray().last())) {
            numAndSignArray[numAndSignArray.lastIndex] = concatNumToNumAndReturnIntNum(numAndSignArray.last(), inputNum).toString()
            return
        }
        numAndSignArray.add(inputNum)
    }

    private fun concatNumToNumAndReturnIntNum(num: String, concatNum: String): Int {
        return num.toDouble().toInt() * DECIMAL_MULTIPLY_NUM + concatNum.toDouble().toInt()
    }


    fun getString(): String {
        return numAndSignArray.joinToString("")
    }

    fun handleInputDelete() {
        if (arraySize == 0) return

        if (numAndSignArray.last().length > 1) {
            numAndSignArray[arraySize-1] = (numAndSignArray.last().toDouble().toInt() / 10 ).toString()
            return
        }

        numAndSignArray.removeLast()
    }

    fun checkExpressionCanCalculated(): Boolean {
        if (Operation.isOperation(numAndSignArray.last())) {
            return false
        }
        return true
    }

    fun handleEquals() {
        var res: String = calculator.calculate(numAndSignArray).toString()

        if (res.toDouble() % 1 == 0.0) {
            res = res.toDouble().toInt().toString()
        }

        numAndSignArray.clear()
        numAndSignArray.add(res)
    }

    companion object {
        const val DECIMAL_MULTIPLY_NUM = 10
    }
}