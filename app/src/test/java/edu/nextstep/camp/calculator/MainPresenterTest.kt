package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    @MockK private lateinit var view: MainContract.View
    @InjectMockKs private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `0부터 9까지 각 버튼을 누르면 화면에 해당 숫자가 표시된다`() {
        (0..9).forEach { number ->
            // given
            presenter = MainPresenter(view)

            // when
            presenter.enterNumber(number)

            // then
            verify { view.showExpression("$number") }
        }
    }

    @Test
    fun `숫자가 입력되어 있을 때 기호를 입력하면 화면에 표시된다`() {
        listOf(
            Operator.Plus to "+",
            Operator.Minus to "-",
            Operator.Multiply to "*",
            Operator.Divide to "/",
        ).forEach { (operator, sign) ->
            // given
            presenter = MainPresenter(view)
            presenter.enterNumber(1)

            // when
            presenter.enterOperator(operator)

            // then
            verify { view.showExpression("1 $sign") }
        }
    }

    @Test
    fun `숫자가 입력되어 있을 때 숫자를 입력하면 화면에 표시된다`() {
        // given
        presenter.enterNumber(1)

        // when
        presenter.enterNumber(2)

        // then
        verify { view.showExpression("12") }
    }

    @Test
    fun `아무것도 입력하지 않았을 때 기호를 누르면 화면에 빈 값이 표시된다`() {
        // when
        presenter.enterOperator(Operator.Plus)

        // then
        verify { view.showExpression("") }
    }

    @Test
    fun `숫자가 입력되어 있을 때 지우기를 하면 한 자리가 지워진다`() {
        // given
        presenter.enterNumber(1)
        presenter.enterNumber(2)

        // when
        presenter.removeLast()

        // then
        /* -- 질문 --
         * 여기에서 verify가 removeLast 때문에 호출된게 아니라 given에서 enterNumber(1) 했을 때 호출된 것으로 확인됨
         * removeLast를 구현하지 않고 비워둔 상태로 이 테스트를 실행해도 pass 됨
         * enterNumber(1)에서의 호출은 무시하고 when에서의 호출만 확인할 수는 없는지..?
         */
        verify { view.showExpression("1") }
    }

    @Test
    fun `아무 입력이 없을 때 지우기를 누르면 빈 값이 표시된다`() {
        // when
        presenter.removeLast()

        // then
        verify { view.showExpression("") }
    }

    @Test
    fun `1 더하기 2 나누기 3 곱하기 4가 입력되어 있을 때 계산을 하면 4가 표시된다`() {
        // given
        presenter.enterNumber(1)
        presenter.enterOperator(Operator.Plus)
        presenter.enterNumber(2)
        presenter.enterOperator(Operator.Divide)
        presenter.enterNumber(3)
        presenter.enterOperator(Operator.Multiply)
        presenter.enterNumber(4)

        // when
        presenter.calculate()

        // then
        verify { view.showExpression("4") }
    }

    @Test
    fun `완성되지 않은 수식을 계산하면 오류 메세지를 표시한다`() {
        // given
        presenter.enterNumber(1)
        presenter.enterOperator(Operator.Plus)

        // when
        presenter.calculate()

        // then
        verify { view.showIncomplete() }
    }
}
