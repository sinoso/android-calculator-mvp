package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        button0.setOnClickListener {
            presenter.addToNumber(0)
        }
        button1.setOnClickListener {
            presenter.addToNumber(1)
        }
        button2.setOnClickListener {
            presenter.addToNumber(2)
        }
        button3.setOnClickListener {
            presenter.addToNumber(3)
        }
        button4.setOnClickListener {
            presenter.addToNumber(4)
        }
        button5.setOnClickListener {
            presenter.addToNumber(5)
        }
        button6.setOnClickListener {
            presenter.addToNumber(6)
        }
        binding.button7.setOnClickListener {
            presenter.addToNumber(7)
        }
        button8.setOnClickListener {
            presenter.addToNumber(8)
        }
        button9.setOnClickListener {
            presenter.addToNumber(9)
        }
        buttonPlus.setOnClickListener {
            presenter.addToPlus()
        }
        buttonMinus.setOnClickListener {
            presenter.addToMinus()
        }
        buttonMultiply.setOnClickListener {
            presenter.addToMultiply()
        }
        buttonDivide.setOnClickListener {
            presenter.addToDivide()
        }
        buttonDelete.setOnClickListener {
            presenter.deleteLastInput()
        }
        buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }

    override fun showExpression(expression: String) = with(binding) {
        textView.text = expression
    }

    override fun showExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
