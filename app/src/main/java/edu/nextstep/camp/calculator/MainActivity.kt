package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private var expression = Expression.EMPTY

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)
        setViewsClickListener()
    }

    override fun onViewUpdated(newExpression: Expression) {
        binding.textView.text = newExpression.toString()
        expression = newExpression
    }

    override fun onExpressionIncomplete() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    private fun setViewsClickListener() {
        binding.run {
            setNumberButtonsClickListener(
                button0, button1, button2, button3, button4, button5, button6, button7, button8,
                button9,
            )
            buttonPlus.setOnClickListener { setOperatorButtonClickListener(Operator.Plus) }
            buttonMinus.setOnClickListener { setOperatorButtonClickListener(Operator.Minus) }
            buttonMultiply.setOnClickListener { setOperatorButtonClickListener(Operator.Multiply) }
            buttonDivide.setOnClickListener { setOperatorButtonClickListener(Operator.Divide) }
            buttonDelete.setOnClickListener { setDeleteButtonClickListener() }
            buttonEquals.setOnClickListener { setEqualsButtonClickListener() }
        }
    }

    private fun setNumberButtonsClickListener(vararg buttons: Button) {
        buttons.forEach { it ->
            it.setOnClickListener {
                presenter.addNumber(expression, (it as Button).text.toString())
            }
        }
    }

    private fun setOperatorButtonClickListener(operator: Operator) {
        presenter.addOperator(expression, operator)
    }

    private fun setDeleteButtonClickListener() {
        presenter.delete(expression)
    }

    private fun setEqualsButtonClickListener() {
        presenter.calculate(expression)
    }
}
