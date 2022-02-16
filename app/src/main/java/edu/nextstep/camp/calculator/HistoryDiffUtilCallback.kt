package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.domain.History

class HistoryDiffUtilCallback : DiffUtil.ItemCallback<History>() {
    override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem.Expression == newItem.Expression && newItem.resultValue == oldItem.resultValue
    }

    override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem == newItem
    }
}