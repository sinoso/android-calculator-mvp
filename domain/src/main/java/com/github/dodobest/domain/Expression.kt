package com.github.dodobest.domain

class Expression {
    fun evaluate(input: String): List<String> {
        val inputValue: String = eraseBlank(input)

        require(inputValue != "") { "빈 공백문자를 입력했습니다." }

        return extractor.extractNumAndSignAll(inputValue)
    }

    fun eraseBlank(inputString: String): String {
        return inputString.replace(" ", "")
    }

    fun isNum(charVal: Char): Boolean {
        if (charVal.code in '0'.code..'9'.code ) {
            return true
        }
        return false
    }

    fun checkNumStartWithZeroAndNotExactZero(inputValue: String, charIndex: Int): Boolean {
        return inputValue[charIndex] == '0' && charIndex < inputValue.length - 1 && isNum(inputValue[charIndex + 1])
    }

    fun checkInputIsNotCorrect(inputValue: String, charIndex: Int) {
        require(Operation.isOperation(inputValue[charIndex].toString())) { "사칙 연산 외 기호가 입력되었습니다." }

        require(charIndex != inputValue.length - 1) { "사칙 연산 뒤에 값이 오지 않았습니다." }

        require(!isDividedWithZero(inputValue, charIndex)) { "0으로 나누는 값은 존재하지 않습니다." }

        throwErrorIfOperationIsConsecutive(inputValue, charIndex)
    }

    fun isNegativeSignNotMinusSign(inputValue: Char, firstIndexOfNum: Array<Int>): Boolean {
        return Operation.convertToOperation(inputValue.toString()) == Operation.MINUS && firstIndexOfNum[0] == NUM_IS_EMPTY
    }

    fun isPositiveSign(inputValue: Char, firstIndexOfNum: Array<Int>): Boolean {
        return Operation.convertToOperation(inputValue.toString()) == Operation.PLUS && firstIndexOfNum[0] == NUM_IS_EMPTY
    }

    private fun isDividedWithZero(inputValue: String, charIndex: Int): Boolean {
        return Operation.convertToOperation(inputValue[charIndex].toString()) == Operation.DIVIDE && inputValue[charIndex+1] == '0'
    }

    private fun throwErrorIfOperationIsConsecutive(inputValue: String, charIndex: Int) {
        if (!Operation.isOperation(inputValue[charIndex+1].toString())) {
            return
        }

        if (checkOperationIsWhatExpect(inputValue, charIndex, Operation.MULTIPLY, Operation.PLUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputValue, charIndex, Operation.MULTIPLY, Operation.MINUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputValue, charIndex, Operation.DIVIDE, Operation.PLUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputValue, charIndex, Operation.DIVIDE, Operation.MINUS)) {
            return
        }

        throw IllegalArgumentException("연산 기호가 연속으로 입력되었습니다.")
    }

    private fun checkOperationIsWhatExpect(inputValue: String, charIndex: Int, firstOperation: Operation, secondOperation: Operation): Boolean {
        return inputValue[charIndex].toString() == firstOperation.getName()
                && inputValue[charIndex+1].toString() == secondOperation.getName()
    }

    companion object {
        const val NUM_IS_EMPTY = -1
        val extractor = Extractor()
    }
}