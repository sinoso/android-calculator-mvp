package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.domain.*
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View
    private lateinit var historyList: CalculateHistoryItems

    @Before
    fun setUp() {
        view = mockk(relaxUnitFun = true)
        presenter = MainPresenter(view, Calculator())
        historyList = CalculateHistoryItems()
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 없을때, 피연산자 3,2,1을 연속해서 추가하면 텍스트뷰에 '321' 이 출력되어야 한다`() {
        // when : 피연산자 3,2,1 추가
        presenter.addOperand("3")
        presenter.addOperand("2")
        presenter.addOperand("1")

        // then : 321 출력
        val expression = Expression(listOf(321))
        verify { view.refreshExpression(expression) }
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 없을때, 연산자를 추가해도 아무런 변화가 없어야 한다`() {
        // when : 연산자 추가
        presenter.addOperator(Operator.Divide)

        // then : 변화 없음
        val expression = Expression()
        verify { view.refreshExpression(expression) }
    }

    @Test
    fun `계산기 텍스트뷰에 피연산자가 있을때, 연산자를 누르면 피연산자와 연산자가 화면에 이어서 출력되어야 한다`() {
        // given : 수식 '6'이 존재할때
        var expression = Expression(listOf(6))
        var result = Expression(listOf(6, Operator.Plus))
        presenter = MainPresenter(view, Calculator(), expression)

        // when : 연산자 '+'를 누르면
        presenter.addOperator(Operator.Plus)

        // then : '6+'이 출력되어야 한다.
        verify { view.refreshExpression(result) }
    }

    @Test
    fun `계산기 텍스트뷰 수식의 마지막이 연산자로 있을때, 연산자를 누르면 연산자가 변경되어야 한다`() {
        // given : 수식이 '9+'로 존재할때
        val expression = Expression(listOf(9, Operator.Plus))
        val result = Expression(listOf(9, Operator.Divide))
        presenter = MainPresenter(view, Calculator(), expression)

        // when : 연산자 '/'를 누르면
        presenter.addOperator(Operator.Divide)

        // then : 연산자 '9/'이 출력되어야 한다.
        verify { view.refreshExpression(result) }
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 있을때, 지우기 버튼을 수식의 마지막 문자가 지워진다`() {
        // given : 수식 '20/10'이 있을때
        val expression = Expression(listOf(20, Operator.Divide, 10))
        val result = Expression(listOf(20, Operator.Divide, 1))
        presenter = MainPresenter(view, Calculator(), expression)

        // when : 지우기 버튼을 누르면
        presenter.removeLast()

        // then : '20/1' 이 출력되어야 한다.
        verify { view.refreshExpression(result) }
    }

    @Test
    fun `계산기 텍스트뷰에 완전한 수식이 있을때, = 버튼을 누르면 수식이 계산되어야 한다`() {
        // given : 완전한 수식 '3+10'이 있을때
        val expression = Expression(listOf(3, Operator.Plus, 10))
        val result = Expression(listOf(13))
        presenter = MainPresenter(view, Calculator(), expression)

        // when : '=' 버튼을 누르면
        presenter.calculate()

        // then : 계산 결과 '13'이 출력 되어야 한다.
        verify { view.refreshExpression(result) }
    }

    @Test
    fun `계산기 텍스트뷰에 불완전한 수식이 있을때, = 버튼을 누르면 "완성되지 않은 수식입니다"라는 토스트가 출력되어야 한다`() {
        // given : 불완전한 수식 '12*'이 있을때
        val expression = Expression(listOf(12, Operator.Multiply))
        presenter = MainPresenter(view, Calculator(), expression)

        // when : '=' 버튼을 누르면
        presenter.calculate()

        // then : "완성되지 않은 수식입니다"라는 토스트가 출력 되어야 한다.
        verify { view.showToastIncompleteExpression() }
    }

    @Test
    fun `저장목록이 비어있을때, 저장 데이터를 저장 할 수 있다`() {
        // when : 수식과 결과를 추가하면
        val expression = Expression(listOf(12, Operator.Plus, 8))
        val result = Expression(listOf(20))
        historyList += CalculateHistoryItem(expression, result)

        // then : 계산 수식과 결과값이 저장된다.
        assertThat(historyList.getItems())
            .isEqualTo(listOf(CalculateHistoryItem(expression, result)))
    }

    @Test
    fun `저장목록에 저장 데이터가 이미 존재할때, 새로운 데이터를 저장하면 추가적으로 저장된다`() {
        // given : 저장 데이터가 이미 존재할때
        val expression = Expression(listOf(8, Operator.Plus, 9))
        val result = Expression(listOf(17))
        historyList += CalculateHistoryItem(expression, result)

        // when : 새로운 저장 데이터를 추가하면
        val newExpression = Expression(listOf(1, Operator.Multiply, 5))
        val newResult = Expression(listOf(5))
        historyList += CalculateHistoryItem(newExpression, newResult)

        // then : 계산 수식과 결과값이 저장된다.
        assertThat(historyList.getItems()).isEqualTo(
                    listOf(
                        CalculateHistoryItem(expression, result),
                        CalculateHistoryItem(newExpression, newResult)
                    )
            )
    }
}