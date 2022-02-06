package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MainContract.View{
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultAdapter = ResultAdapter()
        binding.recyclerView.adapter = resultAdapter
        presenter = MainPresenter(this, resultAdapter)

        setButtonListener()
    }

    private fun setButtonListener() {
        listOf(binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9)
            .forEach{ view -> view.setOnClickListener {
                if (binding.textView.isVisible) presenter.handleInputNum(view.text.toString())
            }}

        listOf(binding.buttonPlus, binding.buttonMinus, binding.buttonMultiply, binding.buttonDivide)
            .forEach{ view -> view.setOnClickListener {
                if (binding.textView.isVisible) presenter.handleInputArithmetic(view.text.toString())
            }}

        binding.buttonDelete.setOnClickListener {
            if (binding.textView.isVisible) presenter.handleInputDelete()
        }

        binding.buttonEquals.setOnClickListener {
            if (binding.textView.isVisible) {
                try {
                    presenter.handleEquals()
                } catch (e: IllegalArgumentException) {
                    showToastMessage(getResourceValue(R.string.incomplete_expression))
                }
            }
        }

        binding.buttonMemory.setOnClickListener {
            switchRecyclerViewVisible()
        }
    }

    override fun switchRecyclerViewVisible() {
        binding.textView.visibility = binding.textView.visibility.xor(View.GONE)
        binding.recyclerView.visibility = binding.recyclerView.visibility.xor(View.GONE)
    }

    override fun refreshTextView(text: String) {
        binding.textView.text = text
    }

    override fun showToastMessage(toastMessage: String) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
    }

    override fun getResourceValue(resourceValue: Int): String {
        return this.resources.getString(resourceValue)
    }
}
