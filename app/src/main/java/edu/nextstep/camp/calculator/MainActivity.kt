package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener {
            presenter.addOperand(0)
        }
        binding.button1.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button2.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button3.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button4.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button5.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button6.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button7.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button8.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button9.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.buttonPlus.setOnClickListener {
            presenter.addOperator(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.addOperator(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.addOperator(Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.addOperator(Operator.Divide)
        }
        binding.buttonDelete.setOnClickListener {
            presenter.removeLastExpression()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showErrorMsg() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
