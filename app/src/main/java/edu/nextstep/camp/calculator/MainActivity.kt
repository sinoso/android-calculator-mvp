package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding

    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener(::onClickNumberButton)
        binding.button1.setOnClickListener(::onClickNumberButton)
        binding.button2.setOnClickListener(::onClickNumberButton)
        binding.button3.setOnClickListener(::onClickNumberButton)
        binding.button4.setOnClickListener(::onClickNumberButton)
        binding.button5.setOnClickListener(::onClickNumberButton)
        binding.button6.setOnClickListener(::onClickNumberButton)
        binding.button7.setOnClickListener(::onClickNumberButton)
        binding.button8.setOnClickListener(::onClickNumberButton)
        binding.button9.setOnClickListener(::onClickNumberButton)

        binding.buttonPlus.setOnClickListener(::onClickSignButton)
        binding.buttonMinus.setOnClickListener(::onClickSignButton)
        binding.buttonMultiply.setOnClickListener(::onClickSignButton)
        binding.buttonDivide.setOnClickListener(::onClickSignButton)

        binding.buttonDelete.setOnClickListener(::onClickDeleteButton)

        binding.buttonEquals.setOnClickListener(::onClickEqualsButton)
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showIncompleteExpressionToast() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    private fun onClickNumberButton(view: View) {
        val operand = (view as Button).text
            .toString()
            .toInt()

        presenter.addToExpression(operand)
    }

    private fun onClickSignButton(view: View) {
        val operator = Operator.of(
            (view as Button).text
                .toString()
        ) ?: return

        presenter.addToExpression(operator)
    }

    private fun onClickDeleteButton(view: View) {
        presenter.removeLatest()
    }

    private fun onClickEqualsButton(view: View) {
        presenter.calculate()
    }
}
