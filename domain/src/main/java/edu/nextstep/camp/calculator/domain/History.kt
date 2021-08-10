package edu.nextstep.camp.calculator.domain

data class History(
    val expression: Expression = Expression.EMPTY,
    val result: Result = Result()
)
