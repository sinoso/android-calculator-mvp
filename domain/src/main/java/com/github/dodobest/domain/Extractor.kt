package com.github.dodobest.domain

class Extractor {
    fun extractNumAndSignAll(inputString: String): List<String> {
        val numAndOperation = MutableList(0) { "" }
        val firstIndexOfNum: Array<Int> = arrayOf(Expression.NUM_IS_EMPTY)

        for (idx in inputString.indices) {
            numAndOperation.addAll(splitNumAndSignOnce(inputString, idx, firstIndexOfNum))
        }
        numAndOperation.add(inputString.slice(IntRange(firstIndexOfNum[0], inputString.length-1)))

        return numAndOperation.toList()
    }

    private fun splitNumAndSignOnce(inputString: String, charIndex: Int, firstIndexOfNum: Array<Int>): List<String> {
        if (expression.isNum(inputString[charIndex])) {
            updateFirstIndexOfNum(inputString, charIndex, firstIndexOfNum)
            return emptyList()
        }
        expression.checkInputIsNotCorrect(inputString, charIndex)

        return createArithmeticOperation(inputString, charIndex, firstIndexOfNum)
    }

    private fun updateFirstIndexOfNum(inputString: String, charIndex: Int, firstIndexOfNum: Array<Int>){
        if (firstIndexOfNum[0] != Expression.NUM_IS_EMPTY) {
            return
        }

        require(!expression.checkNumStartWithZeroAndNotExactZero(inputString, charIndex)) { "0으로 시작하는 숫자는 지원하지 않습니다." }

        firstIndexOfNum[0] = charIndex
    }

    private fun createArithmeticOperation(inputString: String, charIndex: Int, firstIndexOfNum: Array<Int>): List<String> {
        if (expression.isNegativeSignNotMinusSign(inputString[charIndex], firstIndexOfNum)) {
            firstIndexOfNum[0] = charIndex
            return emptyList()
        }

        if (expression.isPositiveSign(inputString[charIndex], firstIndexOfNum)) {
            return emptyList()
        }

        val numAndOperation = MutableList(0) { "" }

        numAndOperation.add(inputString.slice(IntRange(firstIndexOfNum[0], charIndex-1)))
        numAndOperation.add(inputString[charIndex].toString())

        firstIndexOfNum[0] = Expression.NUM_IS_EMPTY

        return numAndOperation.toList()
    }

    companion object {
        val expression = Expression()
    }

}