package com.hxg.idiom.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hxg.idiom.App
import com.hxg.idiom.R
import com.hxg.idiom.db.entity.IdiomEntity
import com.hxg.idiom.util.*
import com.hxg.idiom.widget.ContentView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 成语详情页
 */
class IdiomDetailActivity : AppCompatActivity() {
    private var contentView: ContentView? = null
    private var idiom: IdiomEntity? = null
    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_idiom_detail)

        intent?.let {
            idiom = it.getParcelableExtra(Constants.INTENT_ENTITY)
            id = it.getIntExtra(Constants.INTENT_ID, -1)
        }

        logD("id = $id, idiom = $idiom")

        if (idiom == null && id < 0) {
            T.showToast("参数异常")
            finish()
            return
        }
    }

    override fun onStart() {
        super.onStart()
        contentView = findViewById(R.id.content_view)
        initContent(contentView)
    }

    private fun initContent(view: ContentView?) {
        try {
            view ?: return
            if (idiom != null) {
                view.updateUI(idiom!!)
                return
            }

            if (id < 0) {
                return
            }

            App.INSTANCE.appScope.launch {
                logV(id)
                val dao = App.INSTANCE.mDatabase.idiomDao()
                val entity = dao.getIdiomById(id)
                logD(entity)

                withContext(Dispatchers.Main) {
                    view.updateUI(entity)
                }
            }
        } catch (e: Exception) {
            logE(e.message)
        }
    }
}