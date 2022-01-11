package com.github.weiggle.drawable

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


/**
 * @author wei.li
 * @created on 2021/12/20
 * @desc desc
 *
 */
class ShapeDrawableFactory(private val mContext: Context) : DrawableFactory {

    private var mStrokeWidth = 0f

    @ColorInt
    private var mStrokeColor = 0
    private var mRadiusLT = 0f
    private var mRadiusLB = 0f
    private var mRadiusRT = 0f
    private var mRadiusRB = 0f
    private var mDashWidth = 0f
    private var mDashGap = 0f
    private var mShape = GradientDrawable.RECTANGLE
    private var mAngle = 0

    @ColorInt
    private var mSolidColor = Color.WHITE

    private var mGradientColor: IntArray? = null


    /**
     * 设置线性
     */
    fun lineShape() {
        mShape = GradientDrawable.LINE
    }

    /**
     * 设置椭圆
     */
    fun ovalShape() {
        mShape = GradientDrawable.OVAL
    }

    /**
     * 默认矩形
     */
    fun defaultShape() {
        mShape = GradientDrawable.RECTANGLE
    }

    /**
     * 设置描边宽度
     */
    fun strokeWidth(dp: Float) {
        mStrokeWidth = dp2px(dp)
    }

    fun strokeWidth(dp: Int) {
        mStrokeWidth = dp2px(dp)
    }

    /**
     * 设置描边颜色
     */
    fun strokeColor(@ColorInt color: Int) {
        mStrokeColor = color
    }

    fun strokeColorRes(@ColorRes resId: Int) {
        mStrokeColor = ContextCompat.getColor(mContext, resId)
    }

    /**
     * 设置虚线
     */
    fun dash(dashGap: Int, dashWidth: Int) {
        mDashGap = dp2px(dashGap)
        mDashWidth = dp2px(dashWidth)
    }

    fun dash(dashGap: Float, dashWidth: Float) {
        mDashGap = dp2px(dashGap)
        mDashWidth = dp2px(dashWidth)
    }


    /**
     * 设置圆角
     */
    fun radius(radius: Float) {
        val px = dp2px(radius)
        mRadiusLT = px
        mRadiusRT = px
        mRadiusRB = px
        mRadiusLB = px
    }

    fun radius(radius: Int) {
        val px = dp2px(radius.toFloat())
        mRadiusLT = px
        mRadiusRT = px
        mRadiusRB = px
        mRadiusLB = px
    }

    fun radiusLT(radius: Float) {
        mRadiusLT = dp2px(radius)
    }

    fun radiusRT(radius: Float) {
        mRadiusRT = dp2px(radius)
    }

    fun radiusRB(radius: Float) {
        mRadiusRB = dp2px(radius)
    }

    fun radiusLB(radius: Float) {
        mRadiusLB = dp2px(radius)
    }

    fun radiusLT(radius: Int) {
        mRadiusLT = dp2px(radius)
    }

    fun radiusRT(radius: Int) {
        mRadiusRT = dp2px(radius)
    }

    fun radiusRB(radius: Int) {
        mRadiusRB = dp2px(radius)
    }

    fun radiusLB(radius: Int) {
        mRadiusLB = dp2px(radius)
    }

    /**
     * 设置填充背景颜色
     */
    fun solidColor(@ColorInt color: Int) {
        mSolidColor = color
    }

    fun solidColorRes(@ColorRes resId: Int) {
        mSolidColor = ContextCompat.getColor(mContext, resId)
    }

    fun gradient(
        angle: Int, @ColorInt startColor: Int, @ColorInt endColor: Int,
        @ColorInt centerColor: Int? = null
    ): ShapeDrawableFactory {
        require(angle % 45 == 0) { "requires 'angle' attribute to be a multiple of 45" }
        if (centerColor != null) {
            val color = IntArray(3)
            color[0] = startColor
            color[1] = centerColor
            color[2] = endColor
            mGradientColor = color
        } else {
            val color = IntArray(2)
            color[0] = startColor
            color[1] = endColor
            mGradientColor = color
        }
        mAngle = angle
        return this
    }

    override fun creator(): Drawable {
        return gradientDrawable(
            mShape, mAngle,
            mStrokeColor,
            mSolidColor,
            mStrokeWidth,
            mRadiusLT,
            mRadiusRT,
            mRadiusRB,
            mRadiusLB,
            mDashWidth,
            mDashGap,
            mGradientColor
        )
    }

    private fun gradientDrawable(
        shape: Int,
        angle: Int,
        @ColorInt strokeColor: Int,
        @ColorInt solidColor: Int,
        strokeWidth: Float,
        radiusLT: Float,
        radiusRT: Float,
        radiusRB: Float,
        radiusLB: Float,
        dashWidth: Float,
        dashGap: Float,
        gradientColor: IntArray? = null
    ): Drawable {
        val drawable: GradientDrawable = if (gradientColor != null) {
            val orientation = when (angle % 360) {
                45 -> GradientDrawable.Orientation.BL_TR
                90 -> GradientDrawable.Orientation.BOTTOM_TOP
                135 -> GradientDrawable.Orientation.BR_TL
                180 -> GradientDrawable.Orientation.RIGHT_LEFT
                225 -> GradientDrawable.Orientation.TR_BL
                270 -> GradientDrawable.Orientation.TOP_BOTTOM
                315 -> GradientDrawable.Orientation.TL_BR
                0 -> GradientDrawable.Orientation.LEFT_RIGHT
                else -> GradientDrawable.Orientation.LEFT_RIGHT
            }
            GradientDrawable(orientation, gradientColor)
        } else {
            GradientDrawable().apply { setColor(solidColor) }
        }

        if (strokeWidth > 0) {
            if (dashWidth > 0 && dashGap > 0) {
                drawable.setStroke(strokeWidth.toInt(), strokeColor, dashWidth, dashGap)
            } else {
                drawable.setStroke(strokeWidth.toInt(), strokeColor)
            }
        }

        val array = floatArrayOf(
            radiusLT, radiusLT,
            radiusRT, radiusRT,
            radiusRB, radiusRB,
            radiusLB, radiusLB
        )
        drawable.cornerRadii = array
        drawable.shape = shape
        return drawable
    }
}