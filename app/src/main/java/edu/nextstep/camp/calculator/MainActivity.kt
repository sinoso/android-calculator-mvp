package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import com.github.dodobest.domain.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val inputHandler = InputHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listOf(binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9)
            .forEach{ view -> view.setOnClickListener {
                inputHandler.handleInputNum(view.text.toString())
                refreshTextView()
            }}

        listOf(binding.buttonPlus, binding.buttonMinus, binding.buttonMultiply, binding.buttonDivide)
            .forEach{ view -> view.setOnClickListener {
                inputHandler.handleInputArithmetic(view.text.toString())
                refreshTextView()
            }}

        binding.buttonDelete.setOnClickListener {
            inputHandler.handleInputDelete()
            refreshTextView()
        }

        binding.buttonEquals.setOnClickListener {
            if (inputHandler.checkExpressionCanCalculated()) {
                inputHandler.handleEquals()
                refreshTextView()
            } else {
                Toast.makeText(this, "완성되지 않은 수식입니다", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun refreshTextView() {
        binding.textView.text = inputHandler.getString()
    }
}
