package com.github.dodobest.domain

class Expression {
    fun evaluate(input: String): List<String> {
        val inputString: String = eraseBlank(input)

        require(inputString != "") { "빈 공백문자를 입력했습니다." }

        return extractor.extractNumAndSignAll(inputString)
    }

    private fun eraseBlank(inputString: String): String {
        return inputString.replace(" ", "")
    }

    fun isNum(charVal: Char): Boolean {
        if (charVal.code in '0'.code..'9'.code ) {
            return true
        }
        return false
    }

    fun checkNumStartWithZeroAndNotExactZero(inputString: String, charIndex: Int): Boolean {
        return inputString[charIndex] == '0' && charIndex < inputString.length - 1 && isNum(inputString[charIndex + 1])
    }

    fun checkInputIsNotCorrect(inputString: String, charIndex: Int) {
        require(Operation.isOperation(inputString[charIndex].toString())) { "사칙 연산 외 기호가 입력되었습니다." }

        require(charIndex != inputString.length - 1) { "사칙 연산 뒤에 값이 오지 않았습니다." }

        require(!isDividedWithZero(inputString, charIndex)) { "0으로 나누는 값은 존재하지 않습니다." }

        throwErrorIfOperationIsConsecutive(inputString, charIndex)
    }

    fun isNegativeSignNotMinusSign(inputChar: Char, firstIndexOfNum: Array<Int>): Boolean {
        return Operation.convertToOperation(inputChar.toString()) == Operation.MINUS && firstIndexOfNum[0] == NUM_IS_EMPTY
    }

    fun isPositiveSign(inputChar: Char, firstIndexOfNum: Array<Int>): Boolean {
        return Operation.convertToOperation(inputChar.toString()) == Operation.PLUS && firstIndexOfNum[0] == NUM_IS_EMPTY
    }

    private fun isDividedWithZero(inputString: String, charIndex: Int): Boolean {
        return Operation.convertToOperation(inputString[charIndex].toString()) == Operation.DIVIDE && inputString[charIndex+1] == '0'
    }

    private fun throwErrorIfOperationIsConsecutive(inputString: String, charIndex: Int) {
        if (!Operation.isOperation(inputString[charIndex+1].toString())) {
            return
        }

        if (checkOperationIsWhatExpect(inputString, charIndex, Operation.MULTIPLY, Operation.PLUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputString, charIndex, Operation.MULTIPLY, Operation.MINUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputString, charIndex, Operation.DIVIDE, Operation.PLUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputString, charIndex, Operation.DIVIDE, Operation.MINUS)) {
            return
        }

        throw IllegalArgumentException("연산 기호가 연속으로 입력되었습니다.")
    }

    private fun checkOperationIsWhatExpect(inputString: String, charIndex: Int, firstOperation: Operation, secondOperation: Operation): Boolean {
        return inputString[charIndex].toString() == firstOperation.getName()
                && inputString[charIndex+1].toString() == secondOperation.getName()
    }

    companion object {
        const val NUM_IS_EMPTY = -1
        val extractor = Extractor()
    }
}