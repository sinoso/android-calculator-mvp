package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.domain.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter
    private val historyAdapter = HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this, Calculator())
        initViews()
    }

    private fun addOperandButtonListener(button: Button) {
        button.setOnClickListener {
            presenter.addOperand(button.text.toString())
        }
    }

    private fun addButtonListener() {
        listOf(
            binding.button0, binding.button1,
            binding.button2, binding.button3,
            binding.button4, binding.button5,
            binding.button6, binding.button7,
            binding.button8, binding.button9,
        ).forEach { addOperandButtonListener(it) }

        binding.buttonPlus.setOnClickListener { presenter.addOperator(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.addOperator(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.addOperator(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.addOperator(Operator.Divide) }
        binding.buttonDelete.setOnClickListener { presenter.removeLast() }
        binding.buttonEquals.setOnClickListener { presenter.calculate() }
        binding.buttonMemory.setOnClickListener { toggleHistoryView() }
    }

    private fun initViews() {
        addButtonListener()
        binding.recyclerView.adapter = historyAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun refreshExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showToastIncompleteExpression() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun toggleHistoryView() {
        if (binding.recyclerView.visibility == View.VISIBLE) {
            binding.textView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            return
        }

        if (binding.recyclerView.visibility == View.GONE) {
            binding.textView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            return
        }
    }

    override fun refreshHistory(historyItems: CalculateHistoryItems) {
        historyAdapter.submitList(historyItems.getItems())
    }

}
