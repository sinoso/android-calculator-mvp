package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity() : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberButtonList: List<Button> = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9
        )
        val operatorButtonList: List<Pair<Button, Operator>> = listOf(
            binding.buttonPlus to Operator.Plus,
            binding.buttonMinus to Operator.Minus,
            binding.buttonMultiply to Operator.Multiply,
            binding.buttonDivide to Operator.Divide
        )

        numberButtonList.forEach{button -> setNumberButtonOnClickListener(button)}
        operatorButtonList.forEach { (button, operator) -> setOperatorButtonOnClickListener(button, operator) }
        binding.buttonDelete.setOnClickListener {
            presenter.removeLastInExpression()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.evaluateExpression()
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    private fun setNumberButtonOnClickListener(button: Button) {
        button.setOnClickListener {
            presenter.addToExpression(button.text.toString().toInt())
        }
    }

    private fun setOperatorButtonOnClickListener(button: Button, operator: Operator) {
        button.setOnClickListener {
            presenter.addToExpression(operator)
        }
    }
}
