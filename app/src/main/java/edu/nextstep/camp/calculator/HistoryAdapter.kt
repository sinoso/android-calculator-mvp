package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

import edu.nextstep.camp.calculator.domain.HistoryData

class HistoryAdapter : ListAdapter<HistoryData, HistoryAdapter.HistoryViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
            val data = getItem(position) as HistoryData
            holder.bind(data)

    }


    class HistoryViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(historyList: HistoryData) {
            binding.tvExpression.text = historyList.expression
            binding.tvResult.text = historyList.result
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HistoryData>() {
            override fun areItemsTheSame(oldItem: HistoryData, newItem: HistoryData) =
                oldItem.hashCode() == newItem.hashCode()

            override fun areContentsTheSame(oldItem: HistoryData, newItem: HistoryData) =
                oldItem == newItem


        }
    }


}