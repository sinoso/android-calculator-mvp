package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class HistoryAdapter : ListAdapter<HistoryModel, HistoryAdapter.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryModel) = with(binding) {
            tvExpression.text = history.expression
            tvResult.text = history.result
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<HistoryModel>() {
            override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean =
                oldItem.expression == newItem.expression

            override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean =
                oldItem == newItem
        }
    }
}
