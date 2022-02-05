package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Histories
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding

    override lateinit var presenter: MainContract.Presenter

    private lateinit var mainHistoryAdapter: MainHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this, Calculator())
        mainHistoryAdapter = MainHistoryAdapter()
        binding.recyclerView.adapter = mainHistoryAdapter

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
        binding.buttonEquals.setOnClickListener { presenter.calculate() }

        binding.buttonMemory.setOnClickListener {
            presenter.changeDisplay(binding.recyclerView.visibility == View.VISIBLE)
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showHistory(histories: Histories) {
        mainHistoryAdapter.submitList(histories.records)
    }

    override fun showIncompleteExpressionToast() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun showHistoryDisplay() {
        binding.textView.visibility = View.INVISIBLE
        binding.recyclerView.visibility = View.VISIBLE
    }

    override fun showCalculateDisplay() {
        binding.textView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.INVISIBLE
    }
}
