package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View
    private lateinit var resultAdapter: ResultAdapter

    @Before
    fun setUp() {
        view = mockk()
        resultAdapter = mockk()
        presenter = MainPresenter(view, resultAdapter)
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
    fun `이전에 입력 3+2x4나누기2-5가 주어졌을 때, =를 누르면 5을 보여준다`() {
        // given
        every { view.refreshTextView(any()) } answers { nothing }
        every { resultAdapter.add(any(), any()) } answers { nothing }

        presenter.handleInputNum("3")
        presenter.handleInputArithmetic("+")
        presenter.handleInputNum("2")
        presenter.handleInputArithmetic("*")
        presenter.handleInputNum("4")
        presenter.handleInputArithmetic("/")
        presenter.handleInputNum("2")
        presenter.handleInputArithmetic("-")
        presenter.handleInputNum("5")

        // when
        presenter.handleEquals()

        // then
        verify { view.refreshTextView("5") }
    }

    @Test
    fun `이전에 입력 3+2x가 주어졌을 때, =를 누르면 IllegalArgumentException 을 발생시킨다`() {
        // given
        every { view.refreshTextView(any()) } answers { nothing }
        every { view.showToastMessage(any()) } answers { nothing }

        presenter.handleInputNum("3")
        presenter.handleInputArithmetic("+")
        presenter.handleInputNum("2")
        presenter.handleInputArithmetic("*")

        // when
        val thrown: IllegalArgumentException = assertThrows(
            IllegalArgumentException::class.java
        ) { presenter.handleEquals() }

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
    }
}