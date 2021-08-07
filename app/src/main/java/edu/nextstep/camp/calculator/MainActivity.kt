package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import com.joseph.domain.Calculator
import com.joseph.domain.Expression
import com.joseph.domain.Operator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            expression += 0
            binding.textView.text = expression.toString()
        }
        binding.button1.setOnClickListener {
            expression += 1
            binding.textView.text = expression.toString()
        }
        binding.button2.setOnClickListener {
            expression += 2
            binding.textView.text = expression.toString()
        }
        binding.button3.setOnClickListener {
            expression += 3
            binding.textView.text = expression.toString()
        }
        binding.button4.setOnClickListener {
            expression += 4
            binding.textView.text = expression.toString()
        }
        binding.button5.setOnClickListener {
            expression += 5
            binding.textView.text = expression.toString()
        }
        binding.button6.setOnClickListener {
            expression += 6
            binding.textView.text = expression.toString()
        }
        binding.button7.setOnClickListener {
            expression += 7
            binding.textView.text = expression.toString()
        }
        binding.button8.setOnClickListener {
            expression += 8
            binding.textView.text = expression.toString()
        }
        binding.button9.setOnClickListener {
            expression += 9
            binding.textView.text = expression.toString()
        }
        binding.buttonPlus.setOnClickListener {
            expression += com.joseph.domain.Operator.Plus
            binding.textView.text = expression.toString()
        }
        binding.buttonMinus.setOnClickListener {
            expression += com.joseph.domain.Operator.Minus
            binding.textView.text = expression.toString()
        }
        binding.buttonMultiply.setOnClickListener {
            expression += com.joseph.domain.Operator.Multiply
            binding.textView.text = expression.toString()
        }
        binding.buttonDivide.setOnClickListener {
            expression += com.joseph.domain.Operator.Divide
            binding.textView.text = expression.toString()
        }
        binding.buttonDelete.setOnClickListener {
            expression = expression.removeLast()
            binding.textView.text = expression.toString()
        }
        binding.buttonEquals.setOnClickListener {
            val result = calculator.calculate(expression.toString())
            if (result == null) {
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            expression = com.joseph.domain.Expression.EMPTY + result
            binding.textView.text = result.toString()
        }
    }
}
