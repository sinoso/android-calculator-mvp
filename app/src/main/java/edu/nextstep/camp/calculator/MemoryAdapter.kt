package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.Memory

class MemoryAdapter : RecyclerView.Adapter<MemoryViewHolder>() {

    private val memoryList = mutableListOf<Memory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        return MemoryViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        holder.bind(memoryList[position])
    }

    override fun getItemCount() = memoryList.size

    fun addItem(memory: Memory) {
        memoryList.add(memory)
        notifyItemInserted(memoryList.size)
    }
}