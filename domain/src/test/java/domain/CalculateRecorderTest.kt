package domain

import com.google.common.truth.Truth.assertThat
import com.joseph.domain.*
import org.junit.jupiter.api.Test

class CalculateRecorderTest {

    private val recorder = CalculateRecorder()
    private val calculator = Calculator()

    @Test
    fun `계산 결과를 한 번 기록하면 records에 하나가 추가된다`() {
        // given
        val expression = Expression(listOf(5, Operator.Plus, 4))
        val calculateResult = calculator.calculate(expression.toString())

        // when
        val record = CalculateRecord(expression, calculateResult!!)
        val recordResult = recorder.recordCalculate(record)

        // then
        val expected = listOf(CalculateRecord(expression, calculateResult))
        assertThat(recordResult.value).isEqualTo(expected)
    }

    @Test
    fun `계산 결과를 다섯 번 기록하면 records에 다섯개 추가된다`() {
        // given
        val expression = Expression(listOf(5, Operator.Plus, 4))
        val calculateResult = calculator.calculate(expression.toString())

        // when
        val record = CalculateRecord(expression, calculateResult!!)
        var recordResult: CalculateRecords? = null
        repeat(5) {
            recordResult = recorder.recordCalculate(record)
        }

        // then
        val expected = listOf(
            CalculateRecord(expression, calculateResult),
            CalculateRecord(expression, calculateResult),
            CalculateRecord(expression, calculateResult),
            CalculateRecord(expression, calculateResult),
            CalculateRecord(expression, calculateResult)
        )
        assertThat(recordResult?.value).isEqualTo(expected)
    }
}