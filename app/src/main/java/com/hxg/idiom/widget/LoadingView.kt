package com.hxg.idiom.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.IntDef
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.hxg.idiom.R
import com.hxg.idiom.util.logE

/**
 * 页面加载中样式
 */
class LoadingView : ConstraintLayout {
    private var loadStatus = LOADING
    private var progressBar: ProgressBar? = null
    private var tipsTv: TextView? = null
    private var retryTv: TextView? = null

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
        LayoutInflater.from(context).inflate(R.layout.loading_view, this)
        progressBar = findViewById(R.id.progress_bar)
        tipsTv = findViewById(R.id.tips_tv)
        retryTv = findViewById(R.id.retry_tv)
        setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        setLoadStyle(loadStatus)
    }

    fun setLoadStatus(@LoadStatus loadStatus: Int) {
        this.loadStatus = loadStatus
        setLoadStyle(loadStatus)
    }

    fun setBgColorRes(@ColorRes id: Int) {
        setBackgroundColor(ContextCompat.getColor(context, id))
    }

    fun setRetryClickListener(listener: OnClickListener) {
        retryTv?.setOnClickListener(listener)
    }

    private fun setLoadStyle(@LoadStatus loadStatus: Int) {
        when (loadStatus) {
            LOADING -> {
                visibility = VISIBLE
                progressBar?.visibility = VISIBLE
                retryTv?.visibility = GONE
            }
            LOAD_SUCCESS -> {
                visibility = GONE
            }

            LOAD_FAILED -> {
                visibility = VISIBLE
                progressBar?.visibility = GONE
                retryTv?.visibility = GONE
            }

            LOAD_RETRY -> {
                visibility = VISIBLE
                progressBar?.visibility = GONE
                retryTv?.visibility = VISIBLE
            }
            else -> {
                logE("参数异常")
            }
        }
    }

    companion object {
        // 加载中
        const val LOADING = 0

        // 加载成功
        const val LOAD_SUCCESS = 1

        // 加载出错
        const val LOAD_FAILED = 2

        // 重试
        const val LOAD_RETRY = 3

        @IntDef(LOADING, LOAD_SUCCESS, LOAD_FAILED, LOAD_RETRY)
        @Retention(AnnotationRetention.SOURCE)
        annotation class LoadStatus
    }
}