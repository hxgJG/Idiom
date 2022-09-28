package com.hxg.idiom.util

import android.content.Context
import android.content.SharedPreferences
import com.hxg.idiom.App
import com.hxg.idiom.BuildConfig

class SpCache private constructor() {
    private val sp: SharedPreferences by lazy {
        App.INSTANCE.getSharedPreferences(Constants.KEY_INIT_DATA_STATE, Context.MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor by lazy { sp.edit() }

    /**
     * 是否已经初始化过
     */
    fun isInitialized(): Boolean {
        val initState = sp.getString(Constants.KEY_INIT_DATA_STATE, "")
        if (BuildConfig.DEBUG) {
            logD("[hxg] initState = $initState")
        }
        return Constants.VALUE_INIT_DATA_STATE == initState
    }

    /**
     * 保存初始化成功的状态
     */
    fun saveInitDataSuccess() {
        editor.putString(Constants.KEY_INIT_DATA_STATE, Constants.VALUE_INIT_DATA_STATE)
        editor.commit()
    }

    companion object {
        @JvmStatic
        fun getInstance() = InnerClass.instance
    }

    private object InnerClass {
        val instance = SpCache()
    }
}