package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: CalculatorContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        presenter = CalculatorPresenter(this)
        setContentView(binding.root)
        setUpOnClickOperandListener()
        setUPOnClickOperatorListener()
        binding.buttonDelete.setOnClickListener { presenter.removeLastExpressionElement() }
        binding.buttonEquals.setOnClickListener { presenter.calculateExpression() }
    }

    private fun setUpOnClickOperandListener() {
        binding.button0.setOnClickListener { presenter.addExpressionElement(0) }
        binding.button1.setOnClickListener { presenter.addExpressionElement(1) }
        binding.button2.setOnClickListener { presenter.addExpressionElement(2) }
        binding.button3.setOnClickListener { presenter.addExpressionElement(3) }
        binding.button4.setOnClickListener { presenter.addExpressionElement(4) }
        binding.button5.setOnClickListener { presenter.addExpressionElement(5) }
        binding.button6.setOnClickListener { presenter.addExpressionElement(6) }
        binding.button7.setOnClickListener { presenter.addExpressionElement(7) }
        binding.button8.setOnClickListener { presenter.addExpressionElement(8) }
        binding.button9.setOnClickListener { presenter.addExpressionElement(9) }
    }

    private fun setUPOnClickOperatorListener() {
        binding.buttonPlus.setOnClickListener { presenter.addExpressionElement(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.addExpressionElement(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.addExpressionElement(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.addExpressionElement(Operator.Divide) }
    }

    override fun refreshExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun notifyInCompleteExpression() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
