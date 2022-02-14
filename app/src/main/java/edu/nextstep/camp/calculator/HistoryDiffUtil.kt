package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.domain.CalculateHistoryItem

class HistoryDiffUtil : DiffUtil.ItemCallback<CalculateHistoryItem>() {
    override fun areItemsTheSame(
        oldItem: CalculateHistoryItem,
        newItem: CalculateHistoryItem
    ): Boolean {
        return oldItem.expression == newItem.expression && oldItem.result == newItem.result
    }

    override fun areContentsTheSame(
        oldItem: CalculateHistoryItem,
        newItem: CalculateHistoryItem
    ): Boolean {
        return oldItem.expression == newItem.expression && oldItem.result == newItem.result
    }
}