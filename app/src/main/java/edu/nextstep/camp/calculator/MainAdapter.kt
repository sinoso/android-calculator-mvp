package edu.nextstep.camp.calculator

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.domain.History

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val items = mutableListOf<History>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(parent = parent)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun replaceItems(items: List<History>) {
        this.items.run {
            clear()
            addAll(items)
        }
    }
}
