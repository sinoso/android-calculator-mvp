package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.Result
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class ResultAdapter() : RecyclerView.Adapter<ViewHolder>() {
    private val results: MutableList<Result> = mutableListOf()

    fun add(expression: String, result: String) {
        results.add(Result(expression, result))
    }

    fun getResults() : List<Result> {
        return results.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemResultBinding = ItemResultBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.expression.text = results[position].expression
        holder.result.text = results[position].result
    }

    override fun getItemCount(): Int {
        return results.size
    }
}