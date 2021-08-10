package edu.nextstep.camp.calculator.domain

data class Result(val value: Int = -1) {

    override fun toString(): String = "= $value"
}
