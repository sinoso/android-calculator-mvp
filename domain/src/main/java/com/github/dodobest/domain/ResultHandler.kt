package com.github.dodobest.domain

class ResultHandler {
    private val results: MutableList<Result> = mutableListOf()

    fun add(expression: String, result: String) {
        results.add(Result(expression, result))
    }

    fun getResults(): List<Result> {
        return results.toList()
    }
}