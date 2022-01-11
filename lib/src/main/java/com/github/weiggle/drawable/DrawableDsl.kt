package com.github.weiggle.drawable

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * @author wei.li
 * @created on 2021/12/20
 * @desc desc
 *
 */

inline fun shapeDrawable(context: Context, factory: ShapeDrawableFactory.() -> Unit): Drawable {
    return ShapeDrawableFactory(context).apply(factory).creator()
}

inline fun selectorDrawable(factory: SelectorDrawableFactory.() -> Unit): Drawable {
    return SelectorDrawableFactory().apply(factory).creator()
}

inline fun colorDrawable(factory: ColorDrawableFactory.() -> Unit): Drawable {
    return ColorDrawableFactory().apply(factory).creator()
}

inline fun animationDrawable(context: Context, factory: AnimationDrawableFactory.() -> Unit): AnimationDrawable {
    return AnimationDrawableFactory(context).apply(factory).creator()
}

inline fun layerDrawable(factory: LayerDrawableFactory.() -> Unit): Drawable {
    return LayerDrawableFactory().apply(factory).creator()
}

inline fun rippleDrawable(factory: RippleDrawableFactory.() -> Unit): Drawable {
    return RippleDrawableFactory().apply(factory).creator()
}

internal fun getColor(context: Context, @ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(context, colorRes)
}

internal fun dp2px(dp: Int): Float {
    return Resources.getSystem().displayMetrics.density * dp
}

internal fun dp2px(dp: Float): Float {
    return Resources.getSystem().displayMetrics.density * dp
}

internal typealias DrawableCreator = () -> Drawable