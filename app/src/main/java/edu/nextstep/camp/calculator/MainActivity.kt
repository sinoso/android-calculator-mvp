package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Memory

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter
    private val memoryAdapter: MemoryAdapter by lazy { MemoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        initRecycler()
        initListener()
    }

    private fun initRecycler() {
        binding.recyclerView.adapter = memoryAdapter
    }

    private fun initListener() {
        with(binding) {
            listOf(
                button0, button1, button2, button3, button4,
                button5, button6, button7, button8, button9
            ).forEach { button ->
                button.setOnClickListener {
                    presenter.addOperand(button.text.toString())
                }
            }

            listOf(
                buttonDivide, buttonMultiply, buttonMinus, buttonPlus
            ).forEach { button ->
                button.setOnClickListener {
                    presenter.addOperator(button.text.toString())
                }
            }

            buttonDelete.setOnClickListener { presenter.removeLast() }
            buttonEquals.setOnClickListener { presenter.calculate() }
            buttonMemory.setOnClickListener { presenter.toggleMemory() }
        }
    }

    override fun refreshExpressionView(expression: String) {
        binding.textView.text = expression
    }

    override fun showErrorToast() {
        Toast.makeText(this@MainActivity, "완성되지 않은 수식입니다", Toast.LENGTH_SHORT).show()
    }

    override fun showMemoryView(isVisible: Boolean) {
        binding.recyclerView.isVisible = isVisible
    }

    override fun addMemory(memory: Memory) {
        memoryAdapter.addItem(memory)
    }
}