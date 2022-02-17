package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MemoryCalculateStorageTest {
    private lateinit var storage: MemoryCalculateStorage

    @BeforeEach
    fun setUp() {
        storage = MemoryCalculateStorage()
    }

    @DisplayName("수식과 계산된 결과가 '수식\n= 결과' 형태로 정상 저장된다")
    @Test
    fun saveTest() {
        val formula = Expression(listOf(1, Operator.Plus, 5))
        val result = Expression(listOf(6))

        storage.save(HistoryItem(formula, result))

        val actual = storage.history.first()
        assertThat(actual).isEqualTo(HistoryItem(formula, result))
    }

    @DisplayName("저장된 결과가 오래된 것이 가장 먼저 나오도록 조회된다")
    @Test
    fun multipleSaveTest() {
        initStorageBySave()

        val actual = storage.history
        assertThat(actual).containsExactly(
            HistoryItem(Expression(listOf(1, Operator.Plus, 5)), Expression(listOf(6))),
            HistoryItem(Expression(listOf(10, Operator.Minus, 2, Operator.Multiply, 5)), Expression(listOf(40))),
            HistoryItem(Expression(listOf(1, Operator.Plus, 5)), Expression(listOf(6)))
        )
    }

    private fun initStorageBySave() =
        storage.apply {
                save(HistoryItem(Expression(listOf(1, Operator.Plus, 5)), Expression(listOf(6))))
                save(HistoryItem(Expression(listOf(10, Operator.Minus, 2, Operator.Multiply, 5)), Expression(listOf(40))))
                save(HistoryItem(Expression(listOf(1, Operator.Plus, 5)), Expression(listOf(6))))
        }
}