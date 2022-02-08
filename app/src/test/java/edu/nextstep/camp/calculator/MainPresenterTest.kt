package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.*
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    // ""가 주어졌을 때, "1"을 입력하면 "1"이 보여야 한다.
    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given

        // when
        presenter.addToExpression(1)

        // then
        verify(exactly = 1) { view.showExpression("1") }
    }

    // "1"가 주어졌을 때, "+"을 입력하면 "1 +"이 보여야 한다.
    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view, expression = Expression(listOf(1)))

        // when
        presenter.addToExpression(Operator.Plus)

        // then
        verify(exactly = 1) { view.showExpression("1 +") }
    }

    // "11 + 32"가 주어졌을 때, 화살표 기호를 입력하면 "11 + 3"이 보여야 한다.
    @Test
    fun `수식이 주어졌을 때, 화살표 기호가 입력되면 수식의 마지막 원소를 지워야 한다`() {
        // given
        presenter = MainPresenter(view, expression = Expression(listOf(11, Operator.Plus, 32)))

        // when
        presenter.removeLast()

        // then
        verify(exactly = 1) { view.showExpression("11 + 3") }
    }

    // ""가 주어졌을 때, 화살표 기호를 입력하면 아무 변화가 없어야 한다.
    @Test
    fun `수식이 비어있을 때, 화살표 기호가 입력되면 아무 변화가 없어야 한다`() {
        // given

        // when
        presenter.removeLast()

        // then
        verify(exactly = 1) { view.showExpression("") }
    }

    // "32 + 41"가 주어졌을 때, "="을 입력하면 "73"이 보여야 한다.
    @Test
    fun `유효한 수식이 주어졌을 때, 기호 = 가 입력되면 수식의 결과값을 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view, expression = Expression(listOf(32, Operator.Plus, 41)))

        // when
        presenter.evaluate()

        // then
        verify(exactly = 1) { view.showExpression("73") }
    }

    // "32 +"가 주어졌을 때, "="을 입력하면 토스트가 나타나야 한다
    @Test
    fun `유효하지 않은 수식이 주어졌을 때, 기호 = 가 입력되면 토스트를 보여줘야 한다`() {
        // given
        presenter = MainPresenter(view, expression = Expression(listOf(32, Operator.Plus)))

        // when
        presenter.evaluate()

        // then
        verify(exactly = 1) { view.showIncompleteExpressionToast() }
    }
}
