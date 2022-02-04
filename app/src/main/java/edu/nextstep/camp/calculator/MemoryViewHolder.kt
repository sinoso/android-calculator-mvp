package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.Memory

class MemoryViewHolder(private val view: ItemResultBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: Memory) {
        view.tvExpression.text = item.expression
        view.tvResult.text = "= ${item.result}"
    }
}