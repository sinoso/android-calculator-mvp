package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import io.mockk.*
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View


    @Before
    fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } just Runs

        // when
        presenter.addToNumber(1)

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
        presenter.addToNumber(5)

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
        presenter.addToNumber(1)
        presenter.addToPlus()
        presenter.addToNumber(2)

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
        every { view.showExpression(capture(expressionSlot)) } just Runs
        presenter.addToNumber(1)
        presenter.addToPlus()
        presenter.addToNumber(2)

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("3")
        verify { view.showExpression(actual) }
    }
}