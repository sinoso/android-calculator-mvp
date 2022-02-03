package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun showExpression(expression: String) = with(binding) {
        textView.text = expression
    }

    override fun showExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        presenter = MainPresenter(this)
        setContentView(binding.root)
        setListener()
    }

    private fun setListener() = with(binding) {
        button0.setOnClickListener {
            presenter.inputNumber(0)
        }
        button1.setOnClickListener {
            presenter.inputNumber(1)
        }
        button2.setOnClickListener {
            presenter.inputNumber(2)
        }
        button3.setOnClickListener {
            presenter.inputNumber(3)
        }
        button4.setOnClickListener {
            presenter.inputNumber(4)
        }
        button5.setOnClickListener {
            presenter.inputNumber(5)
        }
        button6.setOnClickListener {
            presenter.inputNumber(6)
        }
        binding.button7.setOnClickListener {
            presenter.inputNumber(7)
        }
        button8.setOnClickListener {
            presenter.inputNumber(8)
        }
        button9.setOnClickListener {
            presenter.inputNumber(9)
        }
        buttonPlus.setOnClickListener {
            presenter.inputPlus()
        }
        buttonMinus.setOnClickListener {
            presenter.inputMinus()
        }
        buttonMultiply.setOnClickListener {
            presenter.inputMultiply()
        }
        buttonDivide.setOnClickListener {
            presenter.inputDivide()
        }
        buttonDelete.setOnClickListener {
            presenter.deleteLast()
        }
        buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }
}
