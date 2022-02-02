package edu.nextstep.camp.calculator

import com.github.dodobest.domain.InputHandler

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private val inputHandler = InputHandler()

    override fun handleInputNum(inputNum: String) {
        inputHandler.handleInputNum(inputNum)
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleInputArithmetic(inputOperation: String) {
        inputHandler.handleInputArithmetic(inputOperation)
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleInputDelete() {
        inputHandler.handleInputDelete()
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleEquals() {
        if (inputHandler.checkExpressionCanCalculated()) {
            inputHandler.handleEquals()
            view.refreshTextView(inputHandler.getString())
            return
        }

        view.showToastMessage("완성되지 않은 수식입니다")
    }
}