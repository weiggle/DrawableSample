package com.github.weiggle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.github.weiggle.databinding.ActivityMainBinding
import com.github.weiggle.drawable.*

class MainActivity : AppCompatActivity() {
    lateinit var mViewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initShapeDrawable()
    }

    private fun initShapeDrawable() {

        mViewBinding.shapeDrawable.background = shapeStrokeDrawableColorInt(this, Color.BLUE, Color.RED, 1, 10)

        //        shapeDrawable(this) {
        //            solidColor(Color.BLUE)
        //            radius(10)
        //            strokeColor(Color.RED)
        //            strokeWidth(1)
        //        }

        mViewBinding.lineDrawable.background = shapeDrawable(this) {
            lineShape()
            dash(10, 5)
            strokeColor(Color.RED)
            strokeWidth(2)
        }

        mViewBinding.gradientDrawable.background = shapeDrawable(this) {
            gradient(0, Color.BLACK, Color.RED, Color.WHITE)
            radius(20)
        }

        mViewBinding.ovalDrawable1.background = shapeDrawable(this) {
            ovalShape()
            dash(10, 5)
            strokeColor(Color.RED)
            strokeWidth(2)
        }

        mViewBinding.ovalDrawable2.background = shapeDrawable(this) {
            ovalShape()
            solidColor(Color.RED)
        }

        mViewBinding.ovalDrawable3.background = shapeDrawable(this) {
            ovalShape()
            solidColor(Color.RED)
            strokeColor(Color.YELLOW)
            strokeWidth(4)
        }
        mViewBinding.stateListDrawable.background = selectorDrawable {

            pressedDrawable {
                shapeDrawable(this@MainActivity) {
                    solidColor(Color.BLUE)
                    radius(8)
                }
            }

            defaultDrawable {
                shapeDrawable(this@MainActivity) {
                    solidColor(Color.GRAY)
                    radius(8)
                }
            }

        }

        mViewBinding.selectedDrawable.setOnClickListener {
            it.isSelected = !it.isSelected
        }

        mViewBinding.selectedDrawable.background = selectorDrawable {
            selectedDrawable {
                shapeDrawable(this@MainActivity) {
                    solidColor(Color.BLUE)
                    radius(8)
                }
            }

            defaultDrawable {
                shapeDrawable(this@MainActivity) {
                    solidColor(Color.GRAY)
                    radius(8)
                }
            }
        }

        mViewBinding.colorDrawable.background = colorDrawable {
            color(Color.GREEN)
        }
        mViewBinding.ripplesDrawable.background = rippleDrawable {
            contentDrawable {
                shapeDrawable(this@MainActivity) {
                    radius(10)
                    solidColor(Color.BLUE)
                }
            }
            rippleColor(Color.RED)
        }

        val animationDrawable = animationDrawable(this) {
            addFrame(R.drawable.living_000, 37)
            addFrame(R.drawable.living_001, 37)
            addFrame(R.drawable.living_002, 37)
            addFrame(R.drawable.living_003, 37)
            addFrame(R.drawable.living_004, 37)
            addFrame(R.drawable.living_005, 37)
        }

        mViewBinding.animationDrawable.background = animationDrawable
        animationDrawable.start()


        mViewBinding.layerDrawable.background = layerDrawable {
            addLayer2(Color.RED, 80)
            addLayer2(Color.BLUE, 60)
            addLayer2(Color.YELLOW, 40)
            addLayer2(Color.BLACK, 20)
        }
    }

    private fun LayerDrawableFactory.addLayer2(color: Int, size: Int) {
        addLayer(
            colorDrawable { color(color) },
            LayerDrawableFactory.LayerInset().apply {
                width(size)
                height(size)
                gravity(Gravity.CENTER)
            }
        )
    }
}