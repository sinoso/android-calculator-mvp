package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Memory

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter
    private val mainHistoryAdapter = MainHistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this, Calculator())
        binding.recyclerView.adapter = mainHistoryAdapter
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        button0.setOnClickListener {
            presenter.addToNumber(button0.text.toString())
        }
        button1.setOnClickListener {
            presenter.addToNumber(button1.text.toString())
        }
        button2.setOnClickListener {
            presenter.addToNumber(button2.text.toString())
        }
        button3.setOnClickListener {
            presenter.addToNumber(button3.text.toString())
        }
        button4.setOnClickListener {
            presenter.addToNumber(button4.text.toString())
        }
        button5.setOnClickListener {
            presenter.addToNumber(button5.text.toString())
        }
        button6.setOnClickListener {
            presenter.addToNumber(button6.text.toString())
        }
        binding.button7.setOnClickListener {
            presenter.addToNumber(button7.text.toString())
        }
        button8.setOnClickListener {
            presenter.addToNumber(button8.text.toString())
        }
        button9.setOnClickListener {
            presenter.addToNumber(button9.text.toString())
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
        buttonMemory.setOnClickListener {
            presenter.isHistoryVisible(binding.recyclerView.isVisible)
        }
    }

    override fun showExpression(expression: String) = with(binding) {
        textView.text = expression
    }

    override fun showExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun memorizeHistory(memory: Memory) {
       mainHistoryAdapter.submitList(memory.histories)
    }


    override fun showHistory() {
        binding.textView.isInvisible = true
        binding.recyclerView.isInvisible = false
    }

    override fun showCalculate() {
        binding.textView.isInvisible = false
        binding.recyclerView.isInvisible = true
    }
}
