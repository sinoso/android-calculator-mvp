package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.domain.CalculationHistory
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter
    private lateinit var historiesAdapter: CalculationHistoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historiesAdapter = CalculationHistoriesAdapter()
        presenter = MainPresenter(this)

        binding.recyclerView.adapter = historiesAdapter
        binding.button0.setOnClickListener { presenter.addToExpression(0) }
        binding.button1.setOnClickListener { presenter.addToExpression(1) }
        binding.button2.setOnClickListener { presenter.addToExpression(2) }
        binding.button3.setOnClickListener { presenter.addToExpression(3) }
        binding.button4.setOnClickListener { presenter.addToExpression(4) }
        binding.button5.setOnClickListener { presenter.addToExpression(5) }
        binding.button6.setOnClickListener { presenter.addToExpression(6) }
        binding.button7.setOnClickListener { presenter.addToExpression(7) }
        binding.button8.setOnClickListener { presenter.addToExpression(8) }
        binding.button9.setOnClickListener { presenter.addToExpression(9) }
        binding.buttonPlus.setOnClickListener { presenter.addToExpression(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.addToExpression(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.addToExpression(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.addToExpression(Operator.Divide) }
        binding.buttonDelete.setOnClickListener { presenter.removeLastOfExpression() }
        binding.buttonEquals.setOnClickListener { presenter.calculateExpression() }
        binding.buttonMemory.setOnClickListener { toggleCalculationHistory() }
    }

    override fun refreshExpression(expression: Expression) {
        binding.textResult.text = expression.toString()
    }

    override fun notifyIncompleteExpression() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun refreshCalculationHistories(histories: List<CalculationHistory>) {
        historiesAdapter.submitList(histories)
    }

    private fun toggleCalculationHistory() {
        val isHistoryVisible = binding.recyclerView.visibility == View.VISIBLE
        binding.recyclerView.visibility = if (isHistoryVisible) View.GONE else View.VISIBLE
        binding.textResult.visibility = if (isHistoryVisible) View.VISIBLE else View.GONE
    }
}
