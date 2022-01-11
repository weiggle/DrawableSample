package com.github.weiggle.drawable

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

/**
 * @author wei.li
 * @created on 2021/12/21
 * @desc desc
 *
 */
class ColorDrawableFactory() : DrawableFactory {

    @ColorInt
    private var color: Int = Color.WHITE


    fun color(@ColorInt color: Int) {
        this.color = color
    }

    override fun creator(): Drawable {
        return ColorDrawable(color)
    }
}