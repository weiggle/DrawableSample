package com.github.weiggle.drawable

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * @author wei.li
 * @created on 2021/12/27
 * @desc desc
 *
 */
class AnimationDrawableFactory(val mContext: Context) : DrawableFactory {

    private var drawableResIds = mutableListOf<Int>()
    private var durations = mutableListOf<Int>()

    fun addFrame(@DrawableRes resId: Int, duration: Int) {
        drawableResIds.add(resId)
        durations.add(duration)
    }

    fun setFrames(animationFrameItems: List<AnimationFrameItem>) {
        animationFrameItems.forEach {
            drawableResIds.add(it.resId)
            durations.add(it.duration)
        }
    }

    override fun creator(): AnimationDrawable {
        return AnimationDrawable().apply {
            drawableResIds.forEachIndexed { index, i ->
                ContextCompat.getDrawable(mContext, i)?.let {
                    addFrame(it, durations[index])
                }
            }
        }
    }

    class AnimationFrameItem(@DrawableRes val resId: Int, val duration: Int)
}

