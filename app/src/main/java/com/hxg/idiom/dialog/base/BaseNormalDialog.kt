package com.hxg.idiom.dialog.base

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.hxg.idiom.R

/**
 * 普通对话框基类
 *
 * @author hxg
 */
abstract class BaseNormalDialog<T>(context: Activity) : BaseDialog<T>(context) {
    /**
     * 点击事件监听
     */
    var onClickListener: OnClickListener? = null
    protected var mLLBackground: View? = null

    /**
     * 标题
     */
    protected var tvTitle: TextView? = null

    /**
     * 内容
     */
    protected var tvContent: TextView? = null

    /**
     * 左侧按钮/默认按钮
     */
    protected var tvLeft: TextView? = null

    /**
     * 右侧按钮
     */
    protected var tvRight: TextView? = null

    /**
     * 底部分割线
     */
    protected var mVerticalLine: View? = null
    protected var mLinearLayout: LinearLayout? = null
    override fun createDialogView(activity: Activity): View {
        mDialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_normal_view, null)
        bindView(mDialogView!!)
        return mDialogView!!
    }

    protected fun bindView(view: View) {
        try {
            mLinearLayout = view.findViewById(R.id.common_ui_dialog_ll)
            mLLBackground = view.findViewById(R.id.ll_dialog_normal_view_bg)
            tvTitle = view.findViewById(R.id.title_tv)
            tvContent = view.findViewById(R.id.content_tv)
            tvLeft = view.findViewById(R.id.tv_cancel)
            tvRight = view.findViewById(R.id.tv_sure)
            mVerticalLine = view.findViewById(R.id.line_vertical)
        } catch (e: Exception) {
            //Do Nothing
        }
        initOnClickListener()
    }

    protected fun initOnClickListener() {
        //mLLBackground不做任何事，只设置Clickable为true拦截点击事件，防止背景点击穿透
        mLLBackground?.isClickable = true
        tvLeft?.setOnClickListener { v -> onClickListener?.onLeftClick(v) }
        tvRight?.setOnClickListener { v -> onClickListener?.onRightClick(v) }
    }

    public override fun initView() {
        super.initView()
        setTitle(getTitle())
        setContent(getContent())
        initData()
    }

    /**
     * 获取对话框标题
     *
     * @return
     */
    protected abstract fun getTitle(): String?

    /**
     * 初始化数据
     */
    protected fun initData() {}

    /**
     * 获取对话框内容
     *
     * @return
     */
    protected abstract fun getContent(): String?


    open fun setTitle(title: String?) {
        val tv = tvTitle ?: return
        tv.text = title
        tv.visibility = if (title.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    /**
     * 设置内容
     */
    open fun setContent(content: String?) {
        tvContent?.text = content
    }

    /**
     * 点击事件监听
     */
    interface OnClickListener {
        fun onLeftClick(v: View)
        fun onRightClick(v: View)
    }

    override fun getAnimatedView(rootView: View): View? {
        return rootView.findViewById(R.id.common_ui_dialog_ll)
    }
}