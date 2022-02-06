package edu.nextstep.camp.calculator.domain

class Memories {
    private val memories = mutableListOf<Memory>()

    val items: List<Memory> get() = memories

    fun add(expression: Expression, result: Int) {
        memories.add(Memory(expression.toString(), result.toString()))
    }

    override fun toString(): String {
        return items.joinToString(",", "[", "]")
    }
}