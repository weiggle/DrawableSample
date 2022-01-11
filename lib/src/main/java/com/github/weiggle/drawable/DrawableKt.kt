@file:JvmName("DrawableKt")

package com.github.weiggle.drawable

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

/**
 * @author wei.li
 * @created on 2021/12/28
 * @desc desc
 *
 */


/**
 * 纯色： ColorInt
 */
fun shapeDrawableColorInt(context: Context, @ColorInt colorInt: Int = Color.WHITE, radius: Int = 0) =
    shapeDrawable(context) {
        solidColor(colorInt)
        radius(radius)
    }

/**
 * 纯色： ColorRes
 */
fun shapeDrawableColorRes(context: Context, @ColorRes colorRes: Int, radius: Int = 0) =
    shapeDrawable(context) {
        solidColorRes(colorRes)
        radius(radius)
    }

/**
 * 描边+纯色： ColorInt
 */
fun shapeStrokeDrawableColorInt(
    context: Context,
    @ColorInt solidColor: Int = Color.WHITE,
    @ColorInt strokeColor: Int = Color.WHITE,
    strokeWidth: Int = 0,
    radius: Int = 0
) = shapeDrawable(context) {
    solidColor(solidColor)
    strokeColor(strokeColor)
    strokeWidth(strokeWidth)
    radius(radius)
}

/**
 * 描边+纯色： ColorRes
 */
fun shapeStrokeDrawableColorRes(
    context: Context,
    @ColorRes solidColorRes: Int,
    @ColorRes strokeColorRes: Int,
    strokeWidth: Int = 0,
    radius: Int = 0
) = shapeDrawable(context) {
    solidColorRes(solidColorRes)
    strokeColorRes(strokeColorRes)
    strokeWidth(strokeWidth)
    radius(radius)
}


/**
 * 渐变： ColorRes
 */
fun gradientDrawableColorRes(
    context: Context,
    @ColorInt startColor: Int,
    @ColorInt endColor: Int,
    @ColorInt centerColor: Int? = null,
    angle: Int = 0,
    radius: Int = 0
) = shapeDrawable(context) {
    gradient(angle, startColor, endColor, centerColor)
    radius(radius)
}

fun colorDrawableColorInt(@ColorInt color: Int) = colorDrawable { color(color) }