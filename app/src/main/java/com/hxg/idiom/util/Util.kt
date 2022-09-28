package com.hxg.idiom.util

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.provider.Settings
import android.util.DisplayMetrics
import com.hxg.idiom.App

object Util {
    private var resources: Resources? = null

    fun getResource(context: Context? = null): Resources {
        if (resources == null) {
            try {
                var ctx: Context? = context
                if (ctx == null) {
                    ctx = App.INSTANCE.mContext
                }
                resources = ctx.resources
            } catch (e: Exception) {
                resources = Resources.getSystem()
            }
        }
        return resources!!
    }

    private var displayMetrics: DisplayMetrics? = null
    private fun getDisplayMetrics(context: Context? = null): DisplayMetrics {
        if (displayMetrics == null) {
            displayMetrics = getResource(context).displayMetrics
        }
        return displayMetrics!!
    }

    /**
     * 获取设备屏幕的宽度
     */
    fun getScreenWidth(context: Context? = null): Int {
        if (context == null) {
            return getDisplayMetrics().widthPixels
        }
        return context.resources?.displayMetrics?.widthPixels ?: 0
    }

    /**
     * 获取设备屏幕的高度
     */
    fun getScreenHeight(context: Context?): Int {
        if (context == null) {
            return getDisplayMetrics().heightPixels
        }
        return context.resources?.displayMetrics?.heightPixels ?: 0
    }

    private val whArray: IntArray by lazy { IntArray(2) }

    /**
     * 获取应用PhoneWindow的宽度与高度px
     */
    fun getPhoneWindowPxWH(context: Context?): IntArray {
        var ctx = context
        if (ctx == null) {
            ctx = App.INSTANCE.mContext
        }

        val config: Configuration =
            getResource(ctx).configuration ?: return empty_int_array_2_elements

        val density = getScreenDensity(context)

        whArray[0] = (config.screenWidthDp * density).toInt()
        whArray[1] = (config.screenHeightDp * density).toInt()

        return whArray
    }

    private fun getScreenDensity(context: Context?): Float {
        return getDisplayMetrics(context).density
    }

    /**
     * sp转化为像素.
     */
    fun spToPx(sp: Float, context: Context? = null): Int {
        val scale = getScreenDensity(context)
        return (sp * scale + 0.5f).toInt()
    }

    /**
     * 密度转换为像素
     */
    fun dpToPx(dp: Float, context: Context? = null): Int {
        val scale = getScreenDensity(context)
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * 像素转换为密度
     */
    fun pxToDp(px: Float, context: Context? = null): Int {
        val scale = getScreenDensity(context)
        return (px / scale + 0.5f).toInt()
    }

    /**
     * px值转换为sp值
     */
    fun pxToSp(pxValue: Float, context: Context? = null): Int {
        val fontScale = getResource(context).displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 获取应用PhoneWindow的宽度dp
     */
    private fun getPhoneWindowWidthDp(context: Context?): Int {
        var ctx = context
        if (ctx == null) {
            ctx = App.INSTANCE.mContext
        }
        return getResource(ctx).configuration?.screenWidthDp ?: 0
    }

    fun isPad(context: Activity?): Boolean {
        val dp = getPhoneWindowWidthDp(context).toFloat()
        // 大于等于600dp, 则是pad,
        return dp >= 600
    }

    fun isSmallScreen(): Boolean {
        val displayMetrics = getDisplayMetrics()
        val dp = displayMetrics.widthPixels / displayMetrics.density
        // 小于360dp 则是小屏手机
        return dp < 360
    }

    /**
     * 设置系统亮度
     */
    fun setScreenBrightnessAuto(activity: Activity?) {
        if (activity == null || activity.window == null) {
            return
        }
        val attrs = activity.window.attributes ?: return
        attrs.screenBrightness = -1.0f
        activity.window.attributes = attrs
    }

    /**
     * 设置屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     * 注意activity本身的color设置
     */
    fun setBackgroundAlpha(activity: Activity?, bgAlpha: Float) {
        if (activity == null || activity.window == null) {
            return
        }
        val lp = activity.window.attributes ?: return
        lp.alpha = bgAlpha
        activity.window.attributes = lp
    }

    /**
     * 根据参数设置系统亮度
     */
    fun setScreenBrightness(activity: Activity?, percent: Int) {
        if (activity == null || activity.window == null) {
            return
        }
        val attrs = activity.window.attributes ?: return
        var p = percent
        if (p < 5) {
            p = 5
        } else if (p > 255) {
            p = 255
        }
        attrs.screenBrightness = p / 255.0f
        activity.window.attributes = attrs
    }

    /**
     * 获取系统亮度
     */
    fun getScreenBrightness(activity: Activity?): Int {
        var value = 0
        if (activity == null) {
            return value
        }
        val cr = activity.contentResolver ?: return value
        try {
            value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: Throwable) {
        }
        return value
    }

    fun convertActivityFromTranslucent(activity: Activity?) {
        activity ?: return
        try {
            val method = Activity::class.java.getDeclaredMethod("convertFromTranslucent")
            method.isAccessible = true
            method.invoke(activity)
        } catch (t: Throwable) {
        }
    }


    /**
     * Calling the convertToTranslucent method on platforms after Android 5.0
     */
    fun convertActivityToTranslucent(activity: Activity?) {
        activity ?: return
        try {
            val getActivityOptions = Activity::class.java.getDeclaredMethod("getActivityOptions")
            getActivityOptions.isAccessible = true
            val options = getActivityOptions.invoke(activity)
            val classes = Activity::class.java.declaredClasses
            var translucentConversionListenerClazz: Class<*>? = null
            for (clazz in classes) {
                if (clazz.simpleName.contains("TranslucentConversionListener")) {
                    translucentConversionListenerClazz = clazz
                }
            }
            val convertToTranslucent = Activity::class.java.getDeclaredMethod(
                "convertToTranslucent",
                translucentConversionListenerClazz, ActivityOptions::class.java
            )
            convertToTranslucent.isAccessible = true
            convertToTranslucent.invoke(activity, null, options)
        } catch (t: Throwable) {
        }
    }
}