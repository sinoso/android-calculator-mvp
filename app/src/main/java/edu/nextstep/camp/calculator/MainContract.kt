package edu.nextstep.camp.calculator

interface MainContract {
    interface View {

        fun refreshTextView(text: String)
        fun showToastMessage(toastMessage: String)
    }
    interface Presenter {

        fun handleInputNum(inputNum: String)
        fun handleInputArithmetic(inputOperation: String)
        fun handleInputDelete()
        fun handleEquals()
    }

}