package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculationMemory

class CalculatorMemoryAdapter :
    ListAdapter<CalculationMemory.Record, CalculatorMemoryAdapter.RecordViewHolder>(
        RecordDiffUtilCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemResultBinding.inflate(layoutInflater, parent, false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecordViewHolder(
        private val binding: ItemResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CalculationMemory.Record) {
            binding.tvExpression.text = item.expression.toString()
            binding.tvResult.text = "=${item.result}"
        }
    }

    private class RecordDiffUtilCallback : DiffUtil.ItemCallback<CalculationMemory.Record>() {
        override fun areItemsTheSame(
            oldItem: CalculationMemory.Record,
            newItem: CalculationMemory.Record
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CalculationMemory.Record,
            newItem: CalculationMemory.Record
        ): Boolean {
            return oldItem == newItem
        }
    }
}
