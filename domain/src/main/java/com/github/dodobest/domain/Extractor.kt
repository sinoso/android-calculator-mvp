package com.github.dodobest.domain

class Extractor {
    fun extractNumAndSignAll(inputValue: String): List<String> {
        val numAndOperation = MutableList(0) { "" }
        val firstIndexOfNum: Array<Int> = arrayOf(Expression.NUM_IS_EMPTY)

        for (idx in inputValue.indices) {
            numAndOperation.addAll(splitNumAndSignOnce(inputValue, idx, firstIndexOfNum))
        }
        numAndOperation.add(inputValue.slice(IntRange(firstIndexOfNum[0], inputValue.length-1)))

        return numAndOperation.toList()
    }

    private fun splitNumAndSignOnce(inputValue: String, charIndex: Int, firstIndexOfNum: Array<Int>): List<String> {
        if (expression.isNum(inputValue[charIndex])) {
            updateFirstIndexOfNum(inputValue, charIndex, firstIndexOfNum)
            return emptyList()
        }
        expression.checkInputIsNotCorrect(inputValue, charIndex)

        return createArithmeticOperation(inputValue, charIndex, firstIndexOfNum)
    }

    private fun updateFirstIndexOfNum(inputValue: String, charIndex: Int, firstIndexOfNum: Array<Int>){
        if (firstIndexOfNum[0] != Expression.NUM_IS_EMPTY) {
            return
        }

        require(!expression.checkNumStartWithZeroAndNotExactZero(inputValue, charIndex)) { "0으로 시작하는 숫자는 지원하지 않습니다." }

        firstIndexOfNum[0] = charIndex
    }

    private fun createArithmeticOperation(inputValue: String, charIndex: Int, firstIndexOfNum: Array<Int>): List<String> {
        if (expression.isNegativeSignNotMinusSign(inputValue[charIndex], firstIndexOfNum)) {
            firstIndexOfNum[0] = charIndex
            return emptyList()
        }

        if (expression.isPositiveSign(inputValue[charIndex], firstIndexOfNum)) {
            return emptyList()
        }

        val numAndOperation = MutableList(0) { "" }

        numAndOperation.add(inputValue.slice(IntRange(firstIndexOfNum[0], charIndex-1)))
        numAndOperation.add(inputValue[charIndex].toString())

        firstIndexOfNum[0] = Expression.NUM_IS_EMPTY

        return numAndOperation.toList()
    }

    companion object {
        val expression = Expression()
    }

}