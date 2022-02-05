package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.Record

class MainHistoryAdapter : ListAdapter<Record, MainHistoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val itemResultBinding: ItemResultBinding) : RecyclerView.ViewHolder(itemResultBinding.root) {
        fun bind(record: Record) {
            itemResultBinding.tvExpression.text = record.expression
            itemResultBinding.tvResult.text = record.result
        }
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem.expression == newItem.expression
            }

            override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem == newItem
            }
        }
    }
}
