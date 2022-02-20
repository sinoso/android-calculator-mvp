package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.model.CalculatorMemoryItem

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: MainContract.Presenter
    private val adapter by lazy { CalculatorAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        initButton()
        initRecyclerView()
    }

    private fun initButton() {
        binding.button0.setOnClickListener {
            presenter.addToExpression(0)
        }
        binding.button1.setOnClickListener {
            presenter.addToExpression(1)
        }
        binding.button2.setOnClickListener {
            presenter.addToExpression(2)
        }
        binding.button3.setOnClickListener {
            presenter.addToExpression(3)
        }
        binding.button4.setOnClickListener {
            presenter.addToExpression(4)
        }
        binding.button5.setOnClickListener {
            presenter.addToExpression(5)
        }
        binding.button6.setOnClickListener {
            presenter.addToExpression(6)
        }
        binding.button7.setOnClickListener {
            presenter.addToExpression(7)
        }
        binding.button8.setOnClickListener {
            presenter.addToExpression(8)
        }
        binding.button9.setOnClickListener {
            presenter.addToExpression(9)
        }
        binding.buttonPlus.setOnClickListener {
            presenter.addToExpression(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.addToExpression(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.addToExpression(Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.addToExpression(Operator.Divide)
        }
        binding.buttonDelete.setOnClickListener {
            presenter.removeLastToExpression()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculateToExpression()
        }
        binding.buttonMemory.setOnClickListener {
            presenter.checkMemoryListVisible()
            presenter.updateMemoryList()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    override fun showExpression(expression: Expression) {
        binding.textView.visibility = View.VISIBLE
        binding.textView.text = expression.toString()
    }

    override fun hideExpression() {
        binding.textView.visibility = View.INVISIBLE
    }

    override fun showToast(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
    }

    override fun showMemoryList() {
        binding.recyclerView.visibility = View.VISIBLE
    }

    override fun hideMemoryList() {
        binding.recyclerView.visibility = View.INVISIBLE
    }

    override fun notifyMemoryList(items: List<CalculatorMemoryItem>) {
        adapter.replaceAll(items)
    }

    override fun getMemoryListVisible() = binding.recyclerView.visibility == View.VISIBLE
}
