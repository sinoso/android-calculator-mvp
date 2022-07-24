package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter =
            MainPresenter(view = this, calculator = Calculator(), expression = Expression.EMPTY)
        setButtons()
    }

    override fun showIncompleteExpression(): Unit =
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()

    override fun showExpression(string: String) {
        binding.textView.text = string
    }

    private fun setButtons() {
        setOperandButtons()
        setOperatorButtons()
        setFunctionalButtons()
    }

    private fun setOperandButtons() {
        with(binding) {
            button0 setOperandAddClickListener 0
            button1 setOperandAddClickListener 1
            button2 setOperandAddClickListener 2
            button3 setOperandAddClickListener 3
            button4 setOperandAddClickListener 4
            button5 setOperandAddClickListener 5
            button6 setOperandAddClickListener 6
            button7 setOperandAddClickListener 7
            button8 setOperandAddClickListener 8
            button9 setOperandAddClickListener 9
        }
    }

    private fun setOperatorButtons() {
        with(binding) {
            buttonPlus setOperatorAddClickListener Operator.Plus
            buttonMinus setOperatorAddClickListener Operator.Minus
            buttonMultiply setOperatorAddClickListener Operator.Multiply
            buttonDivide setOperatorAddClickListener Operator.Divide
        }
    }

    private fun setFunctionalButtons() {
        binding.buttonDelete.setOnClickListener { presenter.removeLast() }
        binding.buttonEquals.setOnClickListener { presenter.proceedCalculation() }
    }

    private infix fun Button.setOperandAddClickListener(operand: Int) =
        setOnClickListener { presenter.addOperandToExpression(operand) }

    private infix fun Button.setOperatorAddClickListener(operator: Operator) =
        setOnClickListener { presenter.addOperatorToExpression(operator) }

}
