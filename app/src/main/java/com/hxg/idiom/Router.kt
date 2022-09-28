package com.hxg.idiom

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.hxg.idiom.db.entity.IdiomEntity
import com.hxg.idiom.ui.detail.IdiomDetailActivity
import com.hxg.idiom.util.Constants


object Router {
    /**
     * 安全启动Activity
     */
    private fun startActivitySafely(context: Context, intent: Intent) {
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 安全启动Activity
     *
     * @param context
     * @param intent
     * @param requestCode 请求码
     */
    fun startActivityForResultSafely(context: Activity, intent: Intent, requestCode: Int) {
        try {
            context.startActivityForResult(intent, requestCode)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 跳转到成语详情页
     *
     * @param context 上下文
     */
    fun startDetailActivity(context: Context?, idiom: IdiomEntity? = null, id: Int = -1) {
        if (context == null || (idiom == null && id < 0)) {
            return
        }

        val intent = Intent(context, IdiomDetailActivity::class.java)
        intent.putExtra(Constants.INTENT_ID, id)
        intent.putExtra(Constants.INTENT_ENTITY, idiom)
        startActivitySafely(context, intent)
    }
}