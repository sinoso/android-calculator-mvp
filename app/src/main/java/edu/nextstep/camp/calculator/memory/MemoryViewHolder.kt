package edu.nextstep.camp.calculator.memory

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.R
import edu.nextstep.camp.calculator.domain.Memory

class MemoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val expressionTextView = itemView.findViewById<TextView>(R.id.tv_expression)
    private val resultTextView = itemView.findViewById<TextView>(R.id.tv_result)

    fun bind(item: Memory.Item) {
        expressionTextView.text = item.expression
        resultTextView.text = "= ${item.result}"
    }
}
