package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    internal fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `빈 수식 모드인 경우 토글이 입력되면 메모리 모드로 변경되고 비어있는 메모리 값을 보여줘야 한다`() {
        // given
        val memorySlot = emptyList<Memory.Item>()
        every { view.showMemory(memorySlot) } answers { nothing }

        // when
        presenter.toggleMode()

        // then
        verify { view.showMemory(memorySlot) }
    }

    @Test
    fun `단일 완성된 수식 모드인 경우 토글이 입력되면 메모리 모드로 변경되고 입력된 메모리 값을 보여줘야 한다 (1)`() {
        // given
        val memorySlot = listOf(Memory.Item("3 + 5", 8))
        every { view.showExpression(any()) } answers { nothing }
        every { view.showMemory(memorySlot) } answers { nothing }

        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(5)
        presenter.evaluateByExpression()

        // when
        presenter.toggleMode()

        // then
        verify { view.showMemory(memorySlot) }
    }

    @Test
    fun `단일 완성된 수식 모드인 경우 토글이 입력되면 메모리 모드로 변경되고 입력된 메모리 값을 보여줘야 한다 (1 - relaxed 적용)`() {
        view = mockk(relaxed = true, relaxUnitFun = true)
        presenter = MainPresenter(view)

        // given
        val memorySlot = listOf(Memory.Item("3 + 5", 8))

        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(5)
        presenter.evaluateByExpression()

        // when
        presenter.toggleMode()

        // then
        verify { view.showMemory(memorySlot) }
    }

    @Test
    fun `복수개 완성된 수식 모드인 경우 토글이 입력되면 메모리 모드로 변경되고 입력된 메모리 값을 보여줘야 한다 (2)`() {
        // given
        val memorySlot = slot<List<Memory.Item>>()

        every { view.showExpression(any()) } answers { nothing }
        every { view.showMemory(capture(memorySlot)) } answers { nothing }

        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(5)
        presenter.evaluateByExpression()

        presenter.removeLastInExpression()

        presenter.addToExpression(10)
        presenter.addToExpression(Operator.Minus)
        presenter.addToExpression(3)
        presenter.evaluateByExpression()

        // when
        presenter.toggleMode()

        // then
        val actual = memorySlot.captured
        assertThat(actual).isEqualTo(
            listOf(
                Memory.Item("3 + 5", 8),
                Memory.Item("10 - 3", 7)
            )
        )
        verify { view.showMemory(memorySlot.captured) }
    }

    @Test
    @DisplayName("Presenter 테스트 예시")
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다 (1)`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    @DisplayName("Presenter 테스트 예시 without Capture")
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다 (2)`() {
        // given
        val expressionSlot = "1"
        every { view.showExpression(expressionSlot) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        verify { view.showExpression(expressionSlot) }
    }

    @Test
    @DisplayName(" -> 1 클릭 -> 1")
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다 (1)`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    @DisplayName("5 + -> 1 클릭 -> 5 + 1")
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다 (2)`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(5)
        presenter.addToExpression(Operator.Plus)

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("5 + 1")
        verify { view.showExpression(actual) }
    }

    @Test
    @DisplayName("8 -> 9 클릭 -> 89")
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(8)

        // when
        presenter.addToExpression(9)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("89")
        verify { view.showExpression(actual) }
    }

    @ParameterizedTest(name = " -> {0} 클릭 -> ")
    @ValueSource(strings = ["+", "-", "*", "/"])
    fun `입력된 피연산자가 없을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다`(sign: String) {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(Operator.of(sign)!!)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify { view.showExpression(actual) }
    }

    @ParameterizedTest(name = "1 -> {0} 클릭 -> 1 {0}")
    @CsvSource(
        "+, 1 + ",
        "-, 1 - ",
        "*, 1 * ",
        "/, 1 / "
    )
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다 (1)`(sign: String, result: String) {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(1)

        // when
        presenter.addToExpression(Operator.of(sign)!!)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo(result)
        verify { view.showExpression(actual) }
    }

    @ParameterizedTest(name = "1 {0} -> - 클릭 -> 1 -")
    @ValueSource(strings = ["+", "-", "*", "/"])
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다 (2)`(sign: String) {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(1)
        presenter.addToExpression(Operator.of(sign)!!)

        // when
        presenter.addToExpression(Operator.Minus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 -")
        verify { view.showExpression(actual) }
    }

    @DisplayName(" -> 지우기 클릭 -> ")
    @Test
    fun `입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.removeLastInExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify { view.showExpression(actual) }
    }

    @DisplayName("32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 -> ")
    @Test
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(3)
        presenter.addToExpression(2)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(1)

        // when
        presenter.removeLastInExpression()

        // then
        var actual = expressionSlot.captured
        assertThat(actual).isEqualTo("32 +")
        verify { view.showExpression(actual) }

        // when
        presenter.removeLastInExpression()

        // then
        actual = expressionSlot.captured
        assertThat(actual).isEqualTo("32")
        verify { view.showExpression(actual) }

        // when
        presenter.removeLastInExpression()

        // then
        actual = expressionSlot.captured
        assertThat(actual).isEqualTo("3")
        verify { view.showExpression(actual) }

        // when
        presenter.removeLastInExpression()

        // then
        actual = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify { view.showExpression(actual) }
    }

    @DisplayName("3 + 2 -> = 클릭 -> 5")
    @Test
    fun `입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)

        // when
        presenter.evaluateByExpression()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("5")
        verify { view.showExpression(actual) }
    }
}