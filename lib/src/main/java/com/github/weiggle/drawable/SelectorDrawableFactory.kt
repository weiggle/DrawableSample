package com.github.weiggle.drawable

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable

/**
 * @author wei.li
 * @created on 2021/12/21
 * @desc desc
 *
 */

class SelectorDrawableFactory : DrawableFactory {

    private var selectedDrawable: Drawable? = null
    private var checkedDrawable: Drawable? = null
    private var pressedDrawable: Drawable? = null
    private var enabledDrawable: Drawable? = null
    private var unableDrawable: Drawable? = null
    private var defaultDrawable: Drawable? = null

    fun selectedDrawable(drawableCreator: DrawableCreator) {
        selectedDrawable = drawableCreator.invoke()
    }

    fun checkedDrawable(drawableCreator: DrawableCreator) {
        checkedDrawable = drawableCreator.invoke()
    }


    fun pressedDrawable(drawableCreator: DrawableCreator) {
        pressedDrawable = drawableCreator.invoke()
    }

    fun enabledDrawable(drawableCreator: DrawableCreator) {
        enabledDrawable = drawableCreator.invoke()
    }


    fun unableDrawable(drawableCreator: DrawableCreator) {
        unableDrawable = drawableCreator.invoke()
    }

    fun defaultDrawable(drawableCreator: DrawableCreator) {
        defaultDrawable = drawableCreator.invoke()
    }


    override fun creator(): Drawable {
        return StateListDrawable().apply {
            addItem(android.R.attr.state_selected, selectedDrawable)
            addItem(android.R.attr.state_checked, checkedDrawable)
            addItem(android.R.attr.state_pressed, pressedDrawable)
            addItem(android.R.attr.state_enabled, enabledDrawable)
            addItem(-android.R.attr.state_enabled, unableDrawable)
            addItem(0, defaultDrawable)
        }
    }

    private fun StateListDrawable.addItem(attr: Int, drawable: Drawable?) {
        drawable?.let { addState(intArrayOf(attr), it) }
    }
}