package com.hxg.idiom.widget

import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.widget.AppCompatTextView
import com.hxg.idiom.util.Util


class ScrollTextView(context: Context) : AppCompatTextView(context) {
    // 蒙层高度
    private var foregroundCoverHeight = 0
    private var minCoverShowHeight = 0

    private val coverPaint: Paint by lazy {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint
    }

    var heightEnough = false

    var shadowStartColor = 0
    var shadowEndColor = 0

    init {
        movementMethod = ScrollingMovementMethod.getInstance()
        isVerticalScrollBarEnabled = true
        foregroundCoverHeight = Util.dpToPx(32f)
        minCoverShowHeight = Util.dpToPx(120f)
        shadowStartColor = Color.parseColor("#CCFFFFFF")
        shadowEndColor = Color.parseColor("#00FFFFFF")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        heightEnough = measuredHeight >= minCoverShowHeight
        isScrollbarFadingEnabled = heightEnough
    }

    fun getLinearGradient(): LinearGradient {
        return LinearGradient(
            0f, (paddingTop + scrollY).toFloat(),
            0f, (foregroundCoverHeight + paddingTop + scrollY).toFloat(),
            shadowStartColor, shadowEndColor, Shader.TileMode.CLAMP
        )
    }

    fun getLinearGradient2(): LinearGradient {
        return LinearGradient(
            0f, (measuredHeight - paddingBottom + scrollY).toFloat(),
            0f, (measuredHeight - paddingBottom - foregroundCoverHeight + scrollY).toFloat(),
            shadowStartColor, shadowEndColor, Shader.TileMode.CLAMP
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 高度大于 foregroundCoverHeight * 3, 才绘制遮罩
        if (heightEnough) {
            try {
                if (canScrollVertically(-1)) {
                    // 绘制 上方 蒙层
                    coverPaint.shader = getLinearGradient()
                    canvas.drawPaint(coverPaint)
                }
                if (canScrollVertically(1)) {
                    // 绘制 下方 蒙层
                    coverPaint.shader = getLinearGradient2()
                    canvas.drawPaint(coverPaint)
                }
            } catch (e: Exception) {
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        requestLayout()
    }
}