package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
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
            presenter.inputNumber(0)

        }
        binding.button1.setOnClickListener {
            presenter.inputNumber(1)

        }
        binding.button2.setOnClickListener {
            presenter.inputNumber(2)

        }
        binding.button3.setOnClickListener {
            presenter.inputNumber(3)

        }
        binding.button4.setOnClickListener {
            presenter.inputNumber(4)

        }
        binding.button5.setOnClickListener {
            presenter.inputNumber(5)

        }
        binding.button6.setOnClickListener {
            presenter.inputNumber(6)

        }
        binding.button7.setOnClickListener {
            presenter.inputNumber(7)

        }
        binding.button8.setOnClickListener {
            presenter.inputNumber(8)

        }
        binding.button9.setOnClickListener {
            presenter.inputNumber(9)

        }
        binding.buttonPlus.setOnClickListener {
            presenter.inputOperator(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.inputOperator(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.inputOperator(Operator.Multiply)

        }
        binding.buttonDivide.setOnClickListener {
            presenter.inputOperator(Operator.Divide)

        }
        binding.buttonDelete.setOnClickListener {
            presenter.removeLast()

        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }

    override fun refreshCount(value: String) {
        binding.textView.text = value
    }

    override fun showToast(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
