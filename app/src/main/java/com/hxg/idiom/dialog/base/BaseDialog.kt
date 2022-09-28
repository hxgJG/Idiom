package com.hxg.idiom.dialog.base

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.hxg.idiom.R

/**
 * 对话框
 *
 * @author hxg
 */
abstract class BaseDialog<T> : IDialogAction {
    /**
     * 该对话框view依赖的activity
     */
    protected val mContext: Activity

    /**
     * 该自定义对话框的名称，用其类名传入，需保证独一无二
     */
    private val mDialogName: String

    /**
     * DecorView下的content
     */
    private val mContentView: ViewGroup

    protected var dialogAction: IDialogAction? = null

    /**
     * 自定义的对话框view
     */
    protected var mDialogView: View? = null

    protected var animView: View? = null

    /**
     * 是否正在显示标志位，显示为true，隐藏为false
     */
    protected var mIsShow = false

    constructor(context: Activity) {
        this.mContext = context
        mDialogName = this.javaClass.name
        val decor = mContext.window.decorView as ViewGroup
        mContentView = decor.findViewById<ViewGroup>(R.id.content)

        if (context is IDialogAction) {
            dialogAction = context
        }
    }

    /**
     * 创建自定义的对话框view，并返回该view
     */
    protected abstract fun createDialogView(activity: Activity): View?

    protected open fun initView() {
        val dialogView = mDialogView ?: return
        if (mContentView.indexOfChild(dialogView) == -1) {
            mContentView.addView(dialogView)
            dialogView.visibility = View.GONE
            animView = getAnimatedView(dialogView)
        }
    }

    protected open fun getAnimatedView(rootView: View): View? {
        return null
    }

    override fun showDialog() {
        mDialogView = createDialogView(mContext)
        val dialogView = mDialogView ?: return
        initView()

        if (animView != null) {
            animView!!.clearAnimation()
            dialogView.visibility = View.VISIBLE
            dialogView.clearAnimation()
            val animation: Animation =
                AnimationUtils.loadAnimation(mContext, R.anim.dialog_abstract_normal_show_animation)
            animView!!.startAnimation(animation)

            animation.start()

            val anim = ObjectAnimator.ofPropertyValuesHolder(
                dialogView,
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
            ).setDuration(100)
            anim.interpolator = LinearInterpolator()
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    dialogView.alpha = 1f
                }

                override fun onAnimationCancel(animation: Animator?) {
                    super.onAnimationCancel(animation)
                    dialogView.alpha = 1f
                }
            })
            anim.start()
        } else {
            dialogView.scaleX = 1f
            dialogView.scaleY = 1f
            dialogView.alpha = 1f
            dialogView.visibility = View.VISIBLE
        }
        mIsShow = true

        dialogAction?.showDialog()
    }

    fun onDismiss(needAnim: Boolean) {
        val dialogView = mDialogView ?: return
        if (needAnim && animView != null) {
            val animation: Animation = AlphaAnimation(1.0f, 0.2f)
            animation.duration = 100
            dialogView.clearAnimation()
            dialogView.startAnimation(animation)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    dialogView.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
        } else {
            dialogView.visibility = View.GONE
        }

        mIsShow = false
        dialogAction?.dismissDialog()
    }

    override fun dismissDialog() {
        onDismiss(true)
    }

    fun getDialogName(): String {
        return mDialogName
    }

    fun isShow(): Boolean {
        val dialogView = mDialogView ?: return false
        val visibility = dialogView.visibility
        return if (visibility == View.VISIBLE) {
            true
        } else if (visibility == View.GONE || visibility == View.INVISIBLE) {
            false
        } else {
            mIsShow
        }
    }

    fun getDialogView(): View? {
        return mDialogView
    }

    /**
     * Activity 返回键拦截
     *
     * @return true: 表示要拦截，false: 不拦截
     */
    open fun interceptOnKeyBack(): Boolean {
        return false
    }
}