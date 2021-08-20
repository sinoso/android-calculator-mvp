package edu.nextstep.camp.calculator.util

import android.view.View
import androidx.core.view.isVisible


fun View.toggleShowAndHide() {
    if(this.isVisible) {
        this.visibility = View.INVISIBLE
    } else {
        this.visibility = View.VISIBLE
    }
}