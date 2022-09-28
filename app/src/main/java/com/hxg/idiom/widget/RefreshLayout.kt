package com.hxg.idiom.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hxg.idiom.R

class RefreshLayout : SwipeRefreshLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        setColorSchemeColors(ContextCompat.getColor(context, R.color.app_color), ContextCompat.getColor(context, R.color.color_222))
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context, R.color.white))
    }
}