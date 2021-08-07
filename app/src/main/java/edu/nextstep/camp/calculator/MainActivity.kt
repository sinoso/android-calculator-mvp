package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(view = this)

        setupListener()
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.incomplete_expression), Toast.LENGTH_SHORT).show()
    }

    private fun setupListener() {
        binding.button0.setOnClickListener {
            presenter.formatExpression(number = 0)
        }
        binding.button1.setOnClickListener {
            presenter.formatExpression(number = 1)
        }
        binding.button2.setOnClickListener {
            presenter.formatExpression(number = 2)
        }
        binding.button3.setOnClickListener {
            presenter.formatExpression(number = 3)
        }
        binding.button4.setOnClickListener {
            presenter.formatExpression(number = 4)
        }
        binding.button5.setOnClickListener {
            presenter.formatExpression(number = 5)
        }
        binding.button6.setOnClickListener {
            presenter.formatExpression(number = 6)
        }
        binding.button7.setOnClickListener {
            presenter.formatExpression(number = 7)
        }
        binding.button8.setOnClickListener {
            presenter.formatExpression(number = 8)
        }
        binding.button9.setOnClickListener {
            presenter.formatExpression(number = 9)
        }
        binding.buttonPlus.setOnClickListener {
            presenter.formatExpression(operator = Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.formatExpression(operator = Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.formatExpression(operator = Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.formatExpression(operator = Operator.Divide)
        }
        binding.buttonDelete.setOnClickListener {
            presenter.deleteExpression()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }
}
