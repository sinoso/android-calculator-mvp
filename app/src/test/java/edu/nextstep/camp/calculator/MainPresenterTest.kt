package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    internal fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `숫자가 입력되면, 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when: 사용자가 피연산자 1을 입력하면
        presenter.addToExpression(1)

        // then: 화면에 '1'이 보여야 한다
        verify { view.showExpression("1") }
    }

    @Test
    fun `입력된 피연산자가 있을 때, 연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // when: 사용자가 피연산자 1과 연산자 +를 입력하면
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)

        // then: 화면에 '1 +'이 보여야 한다
        verify { view.showExpression("1 +") }
    }

    @Test
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식의 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // given: 수식 '32 +'가 주어졌을 때
        presenter.addToExpression(32)
        presenter.addToExpression(Operator.Plus)

        // when: 사용자가 지우기 버튼을 누르면
        presenter.removeLastInExpression()

        // then: 화면에 '32'가 보여야 한다
        verify { view.showExpression("32") }

        // when: 사용자가 지우기 버튼을 누르면
        presenter.removeLastInExpression()

        // then: 화면에 '3'이 보여야 한다
        verify { view.showExpression("3") }

        // when: 사용자가 지우기 버튼을 누르면
        presenter.removeLastInExpression()

        // then: 화면에 ''이 보여야 한다
        verify { view.showExpression("") }
    }

    @Test
    fun `입력된 수식이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        // given: 수식 '3 + 2'가 주어졌을 때
        presenter.addToExpression(3)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)

        // when: 사용자가 = 버튼을 누르면
        presenter.evaluateExpression()

        // then: 화면에 수식의 계산 결과값 '5'기 보여야 한다
        verify { view.showExpression("5") }
    }
}