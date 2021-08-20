package edu.nextstep.camp.calculator.adpaters

import androidx.recyclerview.widget.DiffUtil
import com.joseph.domain.CalculateRecord

class CalculateRecordItemCallback: DiffUtil.ItemCallback<CalculateRecord>() {
    override fun areItemsTheSame(oldItem: CalculateRecord, newItem: CalculateRecord): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CalculateRecord, newItem: CalculateRecord): Boolean {
        return oldItem == newItem
    }
}