package edu.nextstep.camp.calculator.domain

class MemoryRepository {
    private val memoryList = mutableListOf<Memory>()

    fun addMemory(expression: String, result: String): Memory {
        val memory = Memory(expression, result)
        memoryList.add(memory)
        return memory
    }
}