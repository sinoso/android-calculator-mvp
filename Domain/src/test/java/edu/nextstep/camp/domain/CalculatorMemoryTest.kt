package edu.nextstep.camp.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll


/**
 * Created By Malibin
 * on 8월 12, 2021
 */

class CalculatorMemoryTest {

    @Test
    fun `계산 결과와 수식을 기록하면 해당 기록이 쌓인다`() {
        // given
        val calculatorMemory = CalculatorMemory()
        val expression = Expression(1, Operator.Plus, 2)
        val result = 3

        // when
        calculatorMemory.record(expression, result)

        // then
        assertAll(
            { assertThat(calculatorMemory.size()).isEqualTo(1) },
            { assertThat(calculatorMemory.getHistories().size).isEqualTo(1) }
        )
    }

    @Test
    fun `계산 결과와 수식을 기록하면 기존 기록에 해당 기록을 더해 가지고 있다`() {
        // given
        val previousHistory = CalculationHistory(Expression(1, Operator.Plus, 2), 3)
        val calculatorMemory = CalculatorMemory(mutableListOf(previousHistory))

        val newExpression = Expression(3, Operator.Minus, 2)
        val newResult = 1
        val newHistory = CalculationHistory(newExpression, newResult)

        val expectedHistories = listOf(previousHistory, newHistory)

        // when
        calculatorMemory.record(newExpression, newResult)

        // then
        assertAll(
            { assertThat(calculatorMemory.getHistories().size).isEqualTo(2) },
            {
                assertThat(calculatorMemory.getHistories())
                    .containsExactlyElementsIn(expectedHistories).inOrder()
            }
        )
    }
}
