package edu.nextstep.camp.calculator

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.domain.CalculateHistoryItem

class HistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(calculateHistory: CalculateHistoryItem) {
        binding.tvExpression.text = calculateHistory.expression.toString()
        binding.tvResult.text = "= " + calculateHistory.result.toString()
    }

}