package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.History

class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_result, parent, false)
) {

    private val binding: ItemResultBinding = ItemResultBinding.bind(itemView)

    fun bind(item: History) = with(binding) {
        tvExpression.text = item.expression.toString()
        tvResult.text = item.result.toString()
    }
}
