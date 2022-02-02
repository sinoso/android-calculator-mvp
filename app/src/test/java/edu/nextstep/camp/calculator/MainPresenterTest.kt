package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
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
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(1)

        // when
        presenter.addToExpression(Operator.Plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `지우기가 입력되면 마지막에 입력된 수식이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)

        // when
        presenter.removeLatest()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `등호가 입력되면 수식이 계산되고 계산된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)

        // when
        presenter.calculate()

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("3")
        verify { view.showExpression(actual) }
    }
}
