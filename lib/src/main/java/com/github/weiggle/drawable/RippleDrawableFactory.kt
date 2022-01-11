package com.github.weiggle.drawable

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.os.Build.VERSION.*
import androidx.annotation.ColorInt

/**
 * @author wei.li
 * @created on 2021/12/21
 * @desc desc
 *
 */
class RippleDrawableFactory : DrawableFactory {

    @ColorInt
    private var mRippleColor: Int = 0
    private var mRippleRadius: Int = 0
    private var mRadius: Int = 0
    private var mContentDrawable: Drawable? = null

    // 水波纹半径
    fun rippleRadius(radius: Int) {
        this.mRippleRadius = radius
    }

    fun radius(radius: Int) {
        this.mRadius = radius
    }

    fun contentDrawable(drawableCreator: DrawableCreator) {
        this.mContentDrawable = drawableCreator.invoke()
    }

    fun rippleColor(@ColorInt colorId: Int) {
        require(colorId != 0) { "default colorId can not be 0" }
        this.mRippleColor = colorId
    }


    override fun creator(): Drawable {
        val colorStateList = ColorStateList.valueOf(mRippleColor)
        require(mContentDrawable != null) { "contentDrawable can not be null" }

        return if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RippleDrawable(colorStateList, mContentDrawable, null).apply {
                if (SDK_INT >= Build.VERSION_CODES.M) {
                    if (mRippleRadius > 0) radius = mRippleRadius
                }
            }
        } else {
            selectorDrawable {
                radius(mRadius)
                defaultDrawable { mContentDrawable!! }
                pressedDrawable {
                    colorDrawable {
                        color(mRippleColor)
                    }
                }
            }
        }
    }
}