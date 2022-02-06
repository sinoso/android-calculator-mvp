package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MemoryTest {
    private lateinit var memories: Memories

    @BeforeEach
    fun setup() {
        memories = Memories()
    }

    @Test
    fun `처음 계산 기록을 추가하면 Memory의 빈 리스트에 메모리가 추가된다`() {
        // when
        memories.add(Expression(listOf(10, Operator.Plus, 2)), 12)

        // then
        val expected = Memory("10 + 2", "12")
        assertThat(memories.items[0]).isEqualTo(expected)
    }

    @Test
    fun `기존 계산 기록이 존재할 때 새로운 계산을 기록하면 다음 인덱스에 기록이 추가된다`() {
        // given
        memories.add(Expression(listOf(1, Operator.Plus, 2)), 3)

        // when
        memories.add(Expression(listOf(13, Operator.Minus, 9)), 4)

        // then
        val expected = Memory("13 - 9", "4")
        assertThat(memories.items[1]).isEqualTo(expected)
    }
}