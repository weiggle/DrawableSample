package com.github.weiggle.drawable

import android.annotation.TargetApi
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.view.Gravity
import android.view.View

/**
 * @author wei.li
 * @created on 2021/12/27
 * @desc desc
 *
 */
class LayerDrawableFactory : DrawableFactory {

    private val mDrawableList = mutableListOf<Drawable>()
    private val mInsetList = mutableListOf<LayerInset>()

    fun addLayer(layer: Drawable, inset: LayerInset = LayerInset()): LayerDrawableFactory {
        mDrawableList.add(layer)
        mInsetList.add(inset)
        return this
    }

    override fun creator(): Drawable {
        return if (mDrawableList.isEmpty()) ColorDrawable(Color.WHITE)
        else {
            LayerDrawable(mDrawableList.toTypedArray()).apply {
                mDrawableList.forEachIndexed { index, drawable ->
                    val inset = mInsetList[index]
                    setLayerInset(index, inset.mInsetL, inset.mInsetT, inset.mInsetR, inset.mInsetB)
                    setId(index, inset.id)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setLayerGravity(index, inset.mGravity)
                        setLayerSize(index, inset.mWidth, inset.mHeight)
                    }
                }
            }
        }
    }

    class LayerInset {
        var mInsetL = 0
        var mInsetT = 0
        var mInsetR = 0
        var mInsetB = 0
        var mWidth = -1
        var mHeight = -1
        var mGravity = Gravity.NO_GRAVITY
        var id = View.NO_ID

        fun id(id: Int): LayerInset {
            this.id = id
            return this
        }

        fun padding(left: Int, top: Int, right: Int, bottom: Int): LayerInset {
            this.mInsetL = dp2px(left).toInt()
            this.mInsetT = dp2px(top).toInt()
            this.mInsetR = dp2px(right).toInt()
            this.mInsetB = dp2px(bottom).toInt()
            return this
        }

        fun padding(padding: Int): LayerInset {
            val paddingPx = dp2px(padding).toInt()
            mInsetL = paddingPx
            mInsetT = paddingPx
            mInsetR = paddingPx
            mInsetB = paddingPx
            return this
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun width(width: Int): LayerInset {
            this.mWidth = dp2px(width).toInt()
            return this
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun height(height: Int): LayerInset {
            this.mHeight = dp2px(height).toInt()
            return this
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun gravity(gravity: Int): LayerInset {
            this.mGravity = gravity
            return this
        }
    }
}
