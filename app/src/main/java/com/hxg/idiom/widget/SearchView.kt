package com.hxg.idiom.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.hxg.idiom.R

class SearchView : LinearLayout {
    private var editText: EditText? = null
    private var searchTv: TextView? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.search_view, this)
        editText = findViewById(R.id.edit_text)
        searchTv = findViewById(R.id.search_tv)

        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }
}