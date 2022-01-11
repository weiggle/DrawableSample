package com.github.weiggle.drawable

import android.content.Context
import android.content.res.ColorStateList
import android.util.SparseIntArray
import androidx.annotation.ColorRes

/**
 * @author wei.li
 * @created on 2021/12/21
 * @desc desc
 *
 */
class ColorStateListFactory(val mContext: Context) {

    private val stateArray = SparseIntArray()

    @ColorRes
    private var defaultColor: Int = 0


    fun default(@ColorRes colorId: Int) {
        require(colorId != 0) { "default colorId can not be 0" }
        this.defaultColor = colorId
    }

    fun pressed(@ColorRes colorId: Int) {
        val pressed = android.R.attr.state_pressed
        stateArray.put(pressed, colorId)
    }

    fun checked(@ColorRes colorId: Int) {
        val checked = android.R.attr.state_checked
        stateArray.put(checked, colorId)
    }

    fun unChecked(@ColorRes colorId: Int) {
        val checked = -android.R.attr.state_checked
        stateArray.put(checked, colorId)
    }

    fun selected(@ColorRes colorId: Int) {
        val selected = android.R.attr.state_selected
        stateArray.put(selected, colorId)
    }

    fun creator(): ColorStateList {
        val size = stateArray.size() + 1
        val colors = IntArray(size)
        val colorStates = arrayOfNulls<IntArray>(size)
        for (i in 0..stateArray.size()) {
            colorStates[i] = intArrayOf(stateArray.keyAt(i))
            colors[i] = getColor(mContext, stateArray.valueAt(i))
        }

        colorStates[size - 1] = intArrayOf()
        colors[size - 1] = getColor(mContext, defaultColor)
        return ColorStateList(colorStates, colors)
    }
}