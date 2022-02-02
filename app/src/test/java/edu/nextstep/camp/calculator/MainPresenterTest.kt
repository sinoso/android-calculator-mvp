package edu.nextstep.camp.calculator

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
    fun `이전에 입력이 주어지지 않았을때, 3을 누르면 3이 보여야 한다`() {
        // given
         every { view.refreshTextView("3") } answers { nothing }

        // when
        presenter.handleInputNum("3")

        // then
        verify { view.refreshTextView("3") }

    }

    @Test
    fun `이전에 입력 3이 주어 졌을 때, 3을 누르면 33이 보여야 한다`() {
        // given
        every { view.refreshTextView(any()) } answers { nothing }
        presenter.handleInputNum("3")

        // when
        presenter.handleInputNum("3")

        // then
        verify { view.refreshTextView("33") }

    }


    @Test
    fun `이전에 입력이 주어지지 않았을때, +를 누르면 변화가 없다`() {
        // when
        every { view.refreshTextView(any()) } answers { nothing }
        presenter.handleInputArithmetic("+")

        // then
        verify { view.refreshTextView("") }
    }

    @Test
    fun `이전에 입력 3이 주어졌을 때, +를 누르면 3+ 를 보여준다`() {
        // given
        every { view.refreshTextView(any()) } answers { nothing }
        presenter.handleInputNum("3")

        // when
        presenter.handleInputArithmetic("+")

        // then
        verify { view.refreshTextView("3+") }
    }

    @Test
    fun `이전에 입력이 주어지지 않았을 때, 지우기를 누르면 변화가 없다`() {
        // given
        every { view.refreshTextView(any()) } answers { nothing }

        // when
        presenter.handleInputDelete()

        // then
        verify { view.refreshTextView("") }
    }

    @Test
    fun `이전에 입력 3+가 주어졌을 때, 지우기를 누르면 3을 보여준다`() {
        // given
        every { view.refreshTextView(any()) } answers { nothing }
        presenter.handleInputNum("3")
        presenter.handleInputArithmetic("+")

        // when
        presenter.handleInputDelete()

        // then
        verify { view.refreshTextView("3") }
    }

    @Test
    fun `이전에 입력 3+2x5나누기2-2가 주어졌을 때, =를 누르면 3을 보여준다`() {
        // given
        every { view.refreshTextView(any()) } answers { nothing }
        presenter.handleInputNum("3")
        presenter.handleInputArithmetic("+")
        presenter.handleInputNum("2")
        presenter.handleInputArithmetic("*")
        presenter.handleInputNum("5")
        presenter.handleInputArithmetic("/")
        presenter.handleInputNum("2")
        presenter.handleInputArithmetic("-")
        presenter.handleInputNum("2")

        // when
        presenter.handleEquals()

        // then
        verify { view.refreshTextView("3") }
    }

    @Test
    fun `이전에 입력 3+2x가 주어졌을 때, =를 누르면 토스트 메시지를 보여준다`() {
        // given
        every { view.refreshTextView(any()) } answers { nothing }
        every { view.showToastMessage(any()) } answers { nothing }
        presenter.handleInputNum("3")
        presenter.handleInputArithmetic("+")
        presenter.handleInputNum("2")
        presenter.handleInputArithmetic("*")

        // when
        presenter.handleEquals()

        // then
        verify { view.showToastMessage("완성되지 않은 수식입니다") }
    }
}