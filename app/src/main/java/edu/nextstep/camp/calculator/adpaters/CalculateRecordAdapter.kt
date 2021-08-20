package edu.nextstep.camp.calculator.adpaters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joseph.domain.CalculateRecord
import edu.nextstep.camp.calculator.adpaters.CalculateRecordAdapter.*
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class CalculateRecordAdapter : ListAdapter<CalculateRecord, ViewHolder>(CalculateRecordItemCallback()) {

    class ViewHolder(val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemResultBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = getItem(position)
        Log.d("TAG", "${record.expression.toString()} ${record.result.toString()}")
        holder.binding.tvExpression.text = record.expression.toString()
        holder.binding.tvResult.text = "= ${record.result.toString()}"
    }
}