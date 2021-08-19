package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculationHistory

class CalculatorHistoryAdapter : RecyclerView.Adapter<ResultViewHolder>() {
    private val items = mutableListOf<CalculationHistory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder =
        ResultViewHolder(parent)

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(list: List<CalculationHistory>) {
        items.clear()
        items.addAll(list)
    }

    override fun getItemCount() = items.size

}

class ResultViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
) {
    private val binding = ItemResultBinding.bind(itemView)

    fun bind(history: CalculationHistory) {
        with(binding) {
            tvExpression.text = history.expression
            tvResult.text = history.result.toString()
        }
    }
}
