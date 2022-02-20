package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.model.CalculatorMemoryItem

class CalculatorAdapter : ListAdapter<CalculatorMemoryItem, CalculatorAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<CalculatorMemoryItem>() {
        override fun areItemsTheSame(oldItem: CalculatorMemoryItem, newItem: CalculatorMemoryItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CalculatorMemoryItem, newItem: CalculatorMemoryItem) =
            oldItem == newItem


    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun replaceAll(items: List<CalculatorMemoryItem>) {
        submitList(items.toList())
    }

    class ViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CalculatorMemoryItem) {
            binding.tvExpression.text = data.expression
            binding.tvResult.text = "= ${data.result}"
        }
    }
}
