package com.hxg.idiom.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.hxg.idiom.App
import com.hxg.idiom.R

@SuppressLint("InflateParams")
object T {
    private var toast: Toast? = null
    private var newToast: Toast? = null
    private val mHandler = Handler(Looper.getMainLooper())
    private val yOffset: Int by lazy { Util.dpToPx(76f) }

    private val toastView: TextView by lazy {
        val view = LayoutInflater.from(App.INSTANCE.applicationContext)
            .inflate(R.layout.custom_center_toast, null) as TextView

        view.maxWidth = (Util.getScreenWidth() * 0.8f).toInt()

        view
    }

    fun showToast(
        text: String?,
        stringRes: Int = -1,
        context: Context? = App.INSTANCE.mContext,
        duration: Int = Toast.LENGTH_SHORT,
        gravity: Int = Gravity.BOTTOM
    ) {
        if (text.isNullOrEmpty() && stringRes == -1) {
            return
        }

        val ctx = context ?: App.INSTANCE.mContext
        mHandler.post {
            toastView.text = if (text.isNullOrEmpty()) ctx.getString(stringRes) else text
            if (toast != null) {
                toast!!.cancel()
                toast = null
            }

            val t = Toast(ctx.applicationContext)
            toast = t

            t.setGravity(gravity, 0, if (gravity == Gravity.BOTTOM) yOffset else 0)
            t.duration = duration
            t.view = toastView
            t.show()
        }
    }

    fun showNewToast(
        text: String?,
        stringRes: Int = -1,
        context: Context? = null,
        gravity: Int = Gravity.BOTTOM
    ) {
        if (text.isNullOrEmpty() && stringRes == -1) {
            return
        }

        val ctx = context ?: App.INSTANCE.mContext
        mHandler.post {
            toastView.text = if (text.isNullOrEmpty()) ctx.getString(stringRes) else text

            if (newToast != null) {
                newToast!!.cancel()
                newToast = null
            }
            val t = Toast(ctx.applicationContext)
            newToast = t
            t.setGravity(gravity, 0, if (gravity == Gravity.BOTTOM) yOffset else 0)
            t.duration = Toast.LENGTH_SHORT
            t.view = toastView
            t.show()
        }
    }
}