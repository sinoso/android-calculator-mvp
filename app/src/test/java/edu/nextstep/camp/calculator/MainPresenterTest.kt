package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import io.mockk.*
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        // 1. 실제 객체를 사용 (UI 테스트)
        // 2. Fake 객체를 사용
        // 3. 최후의 수단으로 Mockk, spy 객체 사용
        view = mockk(relaxed = true)
        presenter = MainPresenter(view, Calculator())
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.addToNumber("1")

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs
        presenter.addToNumber("5")

        // when
        presenter.addToMinus()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("5 -")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `delete 를 입력하면 최근에 입력된 것이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs
        presenter.addToNumber("1")
        presenter.addToPlus()
        presenter.addToNumber("2")

        // when
        presenter.deleteLastInput()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `equal 을 입력하면 수식이 계산되고 계산된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        presenter.addToNumber("1")
        presenter.addToPlus()
        presenter.addToNumber("2")
        every { view.showExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("3")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `히스토리 내역이 보이지 않는 상태에서 히스토리 버튼을 누르면 디스플레이에 히스토리 내역을 보여줘야 한다`() {

        // when
        presenter.isHistoryVisible(true)

        // then
        verify { view.showHistory() }
    }

    @Test
    fun `히스토리 내역이 보이는 상태에서 히스토리 버튼을 누르면 디스플레이에 히스토리 내역이 보이지 않아야 한다`() {
        // when
        presenter.isHistoryVisible(false)

        // then
        verify { view.showCalculate() }
    }
}