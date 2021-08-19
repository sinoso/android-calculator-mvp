package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.CalculationHistory
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    private val adapter by lazy { CalculatorHistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.recyclerView.adapter = adapter

        binding.button0.setOnClickListener {
            presenter.addOperand(0)
        }
        binding.button1.setOnClickListener {
            presenter.addOperand(1)
        }
        binding.button2.setOnClickListener {
            presenter.addOperand(2)
        }
        binding.button3.setOnClickListener {
            presenter.addOperand(3)
        }
        binding.button4.setOnClickListener {
            presenter.addOperand(4)
        }
        binding.button5.setOnClickListener {
            presenter.addOperand(5)
        }
        binding.button6.setOnClickListener {
            presenter.addOperand(6)
        }
        binding.button7.setOnClickListener {
            presenter.addOperand(7)
        }
        binding.button8.setOnClickListener {
            presenter.addOperand(8)
        }
        binding.button9.setOnClickListener {
            presenter.addOperand(9)
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
        binding.buttonMemory.setOnClickListener {
            val isShowingHistories = binding.recyclerView.isVisible
            presenter.loadHistory(isShowingHistories)
        }
    }

    private fun showHistoriesView(isShown: Boolean) {
        binding.recyclerView.isVisible = isShown
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showErrorMsg() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun showHistories(histories: List<CalculationHistory>) {
        adapter.setList(histories)
        showHistoriesView(true)
    }

    override fun hideHistories() {
        showHistoriesView(false)
    }
}
