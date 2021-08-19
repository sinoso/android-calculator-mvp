package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalculationHistoriesTest {

    @Test
    fun `계산 기록을 추가하면, 계산 기록이 추가되어야 한다`() {

        // given
        val beforeHistories = listOf(
            CalculationHistory("1+2", 3),
            CalculationHistory("1+3", 4),
            CalculationHistory("1+3", 4),
        )
        val histories = CalculationHistories(beforeHistories)
        val newHistory = CalculationHistory("1+3", 3)

        // when
        val resultHistories = histories.addHistory(newHistory)

        // then
        assertThat(resultHistories.list).isEqualTo(beforeHistories + newHistory)
    }
}