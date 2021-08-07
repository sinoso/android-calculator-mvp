package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MainPresenterTest {

    private val view: MainContract.View = mockk(relaxed = true)

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        val presenter = MainPresenter(view = view) as MainContract.Presenter

        presenter.formatExpression(number = 1)

        assertThat(presenter.expression.toString()).isEqualTo("1")
        verify {
            view.showExpression(presenter.expression)
        }
    }

    @Test
    fun `빈 수식에 연산자가 입력되면 수식에 추가되지 않습니다`() {
        val presenter = MainPresenter(view = view) as MainContract.Presenter

        presenter.formatExpression(operator = Operator.Plus)

        assertThat(presenter.expression).isEqualTo(Expression.EMPTY)
        verify {
            view.showExpression(presenter.expression)
        }
    }

    @Test
    fun `수식을 지우면 마지막 수식이 지워지고 지워진 수식을 보여줘야 한다`() {
        val presenter = MainPresenter(
            view = view,
            _expression = Expression(
                values = listOf(2, Operator.Plus, 3, Operator.Multiply, 4, Operator.Divide, 2)
            ),
        ) as MainContract.Presenter

        presenter.deleteExpression()

        assertThat(presenter.expression.toString()).isEqualTo("2 + 3 * 4 /")
        verify {
            view.showExpression(presenter.expression)
        }
    }

    @Test
    fun `완전한 수식을 계산하면 계산된 결과를 보여줘야 한다`() {
        val presenter = MainPresenter(
            view = view,
            _expression = Expression(
                values = listOf(2, Operator.Plus, 3, Operator.Multiply, 4, Operator.Divide, 2)
            )
        ) as MainContract.Presenter

        presenter.calculate()

        assertThat(presenter.expression.toString()).isEqualTo("10")
        verify {
            view.showExpression(presenter.expression)
        }
    }

    @Test
    fun `불완전한 수식을 계산하면 에러가 발생한다`() {
        val presenter = MainPresenter(
            view = view,
            _expression = Expression(
                values = listOf(2, Operator.Plus, 3, Operator.Multiply)
            )
        ) as MainContract.Presenter

        presenter.calculate()
        verify {
            view.showError()
        }
    }
}
