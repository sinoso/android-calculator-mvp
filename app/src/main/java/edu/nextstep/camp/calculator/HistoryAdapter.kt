package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.domain.CalculateHistoryItem

class HistoryAdapter : ListAdapter<CalculateHistoryItem, HistoryViewHolder>(HistoryDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
