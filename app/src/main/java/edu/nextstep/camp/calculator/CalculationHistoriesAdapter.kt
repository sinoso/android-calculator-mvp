package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.domain.CalculationHistory

/**
 * Created By Malibin
 * on 8월 12, 2021
 */

class CalculationHistoriesAdapter :
    ListAdapter<CalculationHistory, CalculationHistoriesAdapter.ViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemResultBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(history: CalculationHistory) {
            binding.tvExpression.text = history.expression.toString()
            binding.tvResult.text = ("=${history.result}")
        }
    }

    private class ItemComparator : DiffUtil.ItemCallback<CalculationHistory>() {
        override fun areItemsTheSame(
            oldItem: CalculationHistory,
            newItem: CalculationHistory
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CalculationHistory,
            newItem: CalculationHistory
        ): Boolean {
            return oldItem == newItem
        }
    }
}
