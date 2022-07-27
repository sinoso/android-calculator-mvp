package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter =
            MainPresenter(
                view = this,
                calculator = Calculator(),
                expression = Expression.EMPTY,
                calculationResultStorage = CalculationResultStorage()
            )
        setButtons()
        setCalculationResultRecyclerView()
    }

    override fun showToastForIncompleteExpressionInputted(): Unit =
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()

    override fun showExpression(string: String) {
        binding.textView.text = string
    }

    override fun changeCalculateResults(calculationResultList: List<CalculationResult>) {
        val adapter = binding.recyclerView.adapter as CalculationHistoryAdapter
        adapter.submitList(calculationResultList)
    }

    private fun setButtons() {
        setOperandButtons()
        setOperatorButtons()
        setFunctionalButtons()
    }

    private fun setOperandButtons() {
        with(binding) {
            button0.setOnClickListener { presenter.addOperandToExpression(0) }
            button1.setOnClickListener { presenter.addOperandToExpression(1) }
            button2.setOnClickListener { presenter.addOperandToExpression(2) }
            button3.setOnClickListener { presenter.addOperandToExpression(3) }
            button4.setOnClickListener { presenter.addOperandToExpression(4) }
            button5.setOnClickListener { presenter.addOperandToExpression(5) }
            button6.setOnClickListener { presenter.addOperandToExpression(6) }
            button7.setOnClickListener { presenter.addOperandToExpression(7) }
            button8.setOnClickListener { presenter.addOperandToExpression(8) }
            button9.setOnClickListener { presenter.addOperandToExpression(9) }
        }
    }

    private fun setOperatorButtons() {
        with(binding) {
            buttonPlus.setOnClickListener { presenter.addOperatorToExpression(Operator.Plus) }
            buttonMinus.setOnClickListener { presenter.addOperatorToExpression(Operator.Minus) }
            buttonMultiply.setOnClickListener { presenter.addOperatorToExpression(Operator.Multiply) }
            buttonDivide.setOnClickListener { presenter.addOperatorToExpression(Operator.Divide) }
        }
    }

    private fun setFunctionalButtons() {
        binding.buttonDelete.setOnClickListener { presenter.removeLastFromExpression() }
        binding.buttonEquals.setOnClickListener { presenter.proceedCalculation() }
        binding.buttonMemory.setOnClickListener { changeCalculationHistoryVisibility() }
    }

    private fun changeCalculationHistoryVisibility() {
        if (binding.recyclerView.isVisible) {
            binding.recyclerView.isVisible = false
            return
        }
        binding.recyclerView.isVisible = true
        presenter.requestChangeCalculateResults()
    }

    private fun setCalculationResultRecyclerView() {
        binding.recyclerView.apply {
            adapter = CalculationHistoryAdapter()
        }
    }
}
