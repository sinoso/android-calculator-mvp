package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import com.github.dodobest.domain.*


class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)

        listOf(binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9)
            .forEach{ view -> view.setOnClickListener {
                presenter.handleInputNum(view.text.toString())
            }}

        listOf(binding.buttonPlus, binding.buttonMinus, binding.buttonMultiply, binding.buttonDivide)
            .forEach{ view -> view.setOnClickListener {
                presenter.handleInputArithmetic(view.text.toString())
            }}

        binding.buttonDelete.setOnClickListener {
            presenter.handleInputDelete()
        }

        binding.buttonEquals.setOnClickListener {
            presenter.handleEquals()
        }
    }


    override fun refreshTextView(text: String) {
        binding.textView.text = text
    }

    override fun showToastMessage(toastMessage: String) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
    }
}
