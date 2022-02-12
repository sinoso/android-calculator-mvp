package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class MemoryTest {

    @Test
    fun `빈 메모리일때, 메모리에 값을 추가 할 수 있어야 한다`() {
        // given
        val memory = Memory.EMPTY
        val item = Memory.Item("3 + 5", 8)

        // when
        val actual = (memory + item).items

        // then
        assertThat(actual).isEqualTo(listOf(item))
    }

    @Test
    fun `메모리에 값이 있는 경우, 메모리에 값 추가시 연속적으로 추가될 수 있어야 한다`() {
        // given
        val default = Memory.Item("3 + 5", 8)
        val memory = Memory(listOf(default))
        val item = Memory.Item("6 / 2", 3)

        // when
        val actual = (memory + item).items

        // then
        assertThat(actual).isEqualTo(listOf(default, item))
    }
}