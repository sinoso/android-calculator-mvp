package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.History

class MainHistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(history: History) {
        binding.tvExpression.text = history.Expression
        binding.tvResult.text = history.resultValue
    }
}