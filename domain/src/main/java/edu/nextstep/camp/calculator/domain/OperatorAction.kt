package edu.nextstep.camp.calculator.domain

fun interface OperatorAction {
    fun calculate(first: Float, second: Float): Float
}