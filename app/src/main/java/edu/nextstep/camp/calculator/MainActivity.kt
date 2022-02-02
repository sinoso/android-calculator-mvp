package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    override val presenter: MainContract.Presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
    }

    private fun setupUi() {
        setupButtonNumbers()
        setupButtonOperators()
        setupButtonDelete()
        setupButtonEquals()
    }

    private fun setupButtonNumbers() {
        val buttonNumbers = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9
        )

        buttonNumbers.forEach(::setButtonNumbersClickListener)
    }

    private fun setButtonNumbersClickListener(button: Button) {
        button.setOnClickListener {
            presenter.addToExpression(button.text.toString().toInt())
        }
    }

    private fun setupButtonOperators() {
        val buttonOperators = listOf(
            binding.buttonPlus to Operator.Plus,
            binding.buttonMinus to Operator.Minus,
            binding.buttonMultiply to Operator.Multiply,
            binding.buttonDivide to Operator.Divide,
        )

        buttonOperators.forEach { (button, operator) ->
            setButtonOperatorsClickListener(button, operator)
        }
    }

    private fun setButtonOperatorsClickListener(button: Button, operator: Operator) {
        button.setOnClickListener {
            presenter.addToExpression(operator)
        }
    }

    private fun setupButtonDelete() {
        binding.buttonDelete.setOnClickListener {
            presenter.removeLastInExpression()
        }
    }

    private fun setupButtonEquals() {
        binding.buttonEquals.setOnClickListener {
            presenter.evaluateByExpression()
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
