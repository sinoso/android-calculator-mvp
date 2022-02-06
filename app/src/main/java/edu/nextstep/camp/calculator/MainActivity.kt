package edu.nextstep.camp.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Memories
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private var expression = Expression.EMPTY

    private val memoryAdapter = MemoryAdapter()

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this, Memories())
        initViews()
    }

    override fun onViewUpdated(newExpression: Expression) {
        binding.textView.text = newExpression.toString()
        expression = newExpression
    }

    override fun onExpressionIncomplete() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun onViewTypeChanged(viewType: CalculatorViewType, memories: Memories) {
        when (viewType) {
            is ExpressionView -> showExpressionView()
            is MemoryView -> showMemoryView(memories)
        }
    }

    private fun showExpressionView() {
        binding.textView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.INVISIBLE
        setViewsEnabled(true)
    }

    private fun showMemoryView(memories: Memories) {
        memoryAdapter.updateMemories(memories)

        binding.textView.visibility = View.INVISIBLE
        binding.recyclerView.visibility = View.VISIBLE
        setViewsEnabled(false)
    }

    private fun initViews() {
        binding.run {
            binding.textView.movementMethod = ScrollingMovementMethod()
            setNumberButtonsClickListener(
                button0, button1, button2, button3, button4, button5, button6, button7, button8,
                button9,
            )
            buttonPlus.setOnClickListener { setOperatorButtonClickListener(Operator.Plus) }
            buttonMinus.setOnClickListener { setOperatorButtonClickListener(Operator.Minus) }
            buttonMultiply.setOnClickListener { setOperatorButtonClickListener(Operator.Multiply) }
            buttonDivide.setOnClickListener { setOperatorButtonClickListener(Operator.Divide) }
            buttonDelete.setOnClickListener { setDeleteButtonClickListener() }
            buttonEquals.setOnClickListener { setEqualsButtonClickListener() }
            buttonMemory.setOnClickListener { toggleViewType() }

            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = memoryAdapter
        }
    }

    private fun setNumberButtonsClickListener(vararg buttons: Button) {
        buttons.forEach { it ->
            it.setOnClickListener {
                presenter.addNumber(expression, (it as Button).text.toString())
            }
        }
    }

    private fun setOperatorButtonClickListener(operator: Operator) {
        presenter.addOperator(expression, operator)
    }

    private fun setDeleteButtonClickListener() {
        presenter.delete(expression)
    }

    private fun setEqualsButtonClickListener() {
        presenter.calculate(expression)
    }

    private fun toggleViewType() {
        presenter.toggleViewType()
    }

    private fun setViewsEnabled(enabled: Boolean) {
        binding.run {
            button0.isEnabled = enabled
            button1.isEnabled = enabled
            button2.isEnabled = enabled
            button3.isEnabled = enabled
            button4.isEnabled = enabled
            button5.isEnabled = enabled
            button6.isEnabled = enabled
            button7.isEnabled = enabled
            button8.isEnabled = enabled
            button9.isEnabled = enabled
            buttonPlus.isEnabled = enabled
            buttonMinus.isEnabled = enabled
            buttonMultiply.isEnabled = enabled
            buttonDivide.isEnabled = enabled
            buttonDelete.isEnabled = enabled
            buttonEquals.isEnabled = enabled
        }
    }
}
