package com.hxg.idiom.util

import android.util.Log
import com.hxg.idiom.BuildConfig

fun logP(log: Any?) {
    if (log == null || !BuildConfig.DEBUG) {
        return
    }
    println("[hxg] $log")
}

fun logV(log: Any?) {
    if (log == null || !BuildConfig.DEBUG) {
        return
    }
    Log.v("[hxg]", log.toString())
}

fun logD(log: Any?) {
    if (log == null || !BuildConfig.DEBUG) {
        return
    }
    Log.d("[hxg]", log.toString())
}

fun logI(log: Any?) {
    if (log == null || !BuildConfig.DEBUG) {
        return
    }
    Log.i("[hxg]", log.toString())
}

fun logW(log: Any?) {
    if (log == null || !BuildConfig.DEBUG) {
        return
    }
    Log.w("[hxg]", log.toString())
}

fun logE(log: Any?) {
    if (log == null || !BuildConfig.DEBUG) {
        return
    }
    Log.e("[hxg]", log.toString())
}