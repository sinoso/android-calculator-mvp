package com.github.dodobest.domain

enum class Operation (
    private val symbol: String,
    private val calculateStrategy: (Double, Double) -> Double
) {
    PLUS("+", { left, right -> left + right }),
    MINUS("-", { left, right -> left - right }),
    MULTIPLY("*", { left, right -> left * right }),
    DIVIDE("/", { left, right -> left / right });

    fun getName(): String = symbol

    fun calculate(left: Double, right: Double): Double {
        return calculateStrategy(left, right)
    }

    companion object {
        fun isOperation(symbol: String): Boolean = values().any{ it.symbol == symbol }

        fun convertToOperation(symbol: String): Operation = values().find { it.symbol == symbol }
                ?: throw IllegalArgumentException("${symbol}을 가진 연산자를 찾을 수 없습니다.")
    }
}