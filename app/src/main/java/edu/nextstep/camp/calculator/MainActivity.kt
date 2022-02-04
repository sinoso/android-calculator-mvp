package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.domain.Expression
import edu.nextstep.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    private val buttonNumberList: List<Button> by lazy {
        listOf(
            binding.button0, binding.button1,
            binding.button2, binding.button3,
            binding.button4, binding.button5,
            binding.button6, binding.button7,
            binding.button8, binding.button9,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        initViews()
    }

    private fun addOperandButtonListener(button: Button) {
        button.setOnClickListener {
            presenter.addOperand(button.text.toString())
        }
    }

    private fun addButtonListener() {
        buttonNumberList.forEach { addOperandButtonListener(it) }
        binding.buttonPlus.setOnClickListener { presenter.addOperator(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.addOperator(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.addOperator(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.addOperator(Operator.Divide) }
        binding.buttonDelete.setOnClickListener { presenter.removeLast() }
        binding.buttonEquals.setOnClickListener { presenter.calculate() }
    }

    private fun initViews() {
        addButtonListener()
    }

    override fun refreshExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showToastIncompleteExpression() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

}
