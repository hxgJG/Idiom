package com.hxg.idiom

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.hxg.idiom.db.IdiomDatabase
import com.hxg.idiom.db.entity.IdiomEntity
import com.hxg.idiom.util.SpCache
import com.hxg.idiom.util.logD
import com.hxg.idiom.util.logE
import com.hxg.idiom.util.logV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class App : Application() {
    val appScope = CoroutineScope(SupervisorJob())

    val mDatabase by lazy { IdiomDatabase.getIdiomDatabase(this) }

    @Volatile
    lateinit var mContext: Context

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        INSTANCE = this
        mContext = base
        initData()
    }

    private fun initData() {
        appScope.launch {
            if (SpCache.getInstance().isInitialized()) {
                return@launch
            }

            logD("开始初始化")

            val dao = mDatabase.idiomDao()
            dao.deleteAll()

            try {
                val open = resources.assets.open("data2.txt")
                val inReader = InputStreamReader(open)
                val reader = BufferedReader(inReader)

                var index = 0
                var tempString: String?
                // 一次读入一行，直到读入null为文件结束
                while (reader.readLine().also { tempString = it } != null) {
                    if (tempString == null) {
                        continue
                    }

                    if (tempString!!.contains("#")) {
                        dao.addIdiom(getIdiomEntity(tempString!!, index))
                        index++
                    }
                }
                SpCache.getInstance().saveInitDataSuccess()
            } catch (e: Exception) {
                logE(e.message)
            }
        }
    }

    private fun getIdiomEntity(idiomInfo: String, index: Int): IdiomEntity {
        if (index < 5) {
            logV(idiomInfo)
        }

        val list = idiomInfo.split("#")
        val size = list.size
        val entity = IdiomEntity()
        // 设置ID
        entity.id = index

        // 设置成语
        if (size > 1) {
            entity.word = list[0]
        }

        // 设置成语的拼音
        if (size > 2) {
            entity.pinyin = list[1]
        }

        // 设置成语的解释
        if (size > 3) {
            entity.explain = list[2]
        }

        // 设置成语的出处
        if (size > 4) {
            entity.from = list[3]
        }

        // 设置成语的使用举例
        if (size > 5) {
            entity.example = list[4]
        }
        return entity
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        lateinit var INSTANCE: App
    }
}