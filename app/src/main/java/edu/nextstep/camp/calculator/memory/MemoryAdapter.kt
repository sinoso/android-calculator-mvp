package edu.nextstep.camp.calculator.memory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.R
import edu.nextstep.camp.calculator.domain.Memory

class MemoryAdapter : ListAdapter<Memory.Item, MemoryViewHolder>(MemoryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return MemoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}