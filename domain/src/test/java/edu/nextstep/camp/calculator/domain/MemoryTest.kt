package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class MemoryTest {

    @Test
    fun `처음 계산 기록을 추가하면 Memory의 히스토리 리스트에 추가된다`() {
        // given
        val memory = Memory.EMPTY

        // when
        val actual = memory + History("2 + 2", "4")

        // then
        val historyList = listOf(History("2 + 2", "4"))
        val expected = Memory(historyList)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `계산 기록이 존재할 때 새로운 계산을 기록하게 되면 기존 계산 기록에서 새 기록이 추가된다`() {
        // given
        val memory = Memory(listOf(History("2 + 2", "4")))

        // when
        val actual = memory + History("9 / 3", "3")

        // then
        val historyList = listOf(History("2 + 2", "4"), History("9 / 3", "3"))
        val expected = Memory(historyList)
        assertThat(actual).isEqualTo(expected)
    }
}