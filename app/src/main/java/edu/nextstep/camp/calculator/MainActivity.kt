package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        initOperandClickListener()
        initOperatorClickListener()

        binding.buttonDelete.setOnClickListener {
            presenter.delete()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }

    private fun initOperatorClickListener() {
        binding.buttonPlus.setOnClickListener {
            presenter.addToExpression(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.addToExpression(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.addToExpression(Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.addToExpression(Operator.Divide)
        }
    }

    private fun initOperandClickListener() {
        listOf(
            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9
        ).forEachIndexed { index, button ->
            button.setOnClickListener {
                presenter.addToExpression(index)
            }
        }
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun onError(exception: Exception) {
        val toastMessage: String = if(exception is IllegalArgumentException) {
            getString(R.string.incomplete_expression)
        } else {
            exception.message ?: getString(R.string.unknown_exception)
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
