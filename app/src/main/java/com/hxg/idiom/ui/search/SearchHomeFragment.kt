package com.hxg.idiom.ui.search

import android.view.View
import com.hxg.idiom.App
import com.hxg.idiom.HomeListView
import com.hxg.idiom.R
import com.hxg.idiom.dialog.base.CollectionUtils
import com.hxg.idiom.util.logE
import com.hxg.idiom.util.logV
import com.hxg.idiom.widget.LoadingView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * 搜索主页
 */
class SearchHomeFragment : BaseSearchFragment() {
    private var loadView: LoadingView? = null

    override fun getLayoutRes(): Int {
        return R.layout.search_home_fragment
    }

    override fun initView(view: View) {
        super.initView(view)
        loadView = view.findViewById(R.id.load_view)
        loadView?.setOnClickListener { updateRandomContent() }
        updateRandomContent()
    }

    override fun initRecyclerView(view: View): HomeListView? {
        return view.findViewById(R.id.recycler_view)
    }

    private fun updateRandomContent() {
        try {
            // start loading
            val rv = recyclerView ?: return
            App.INSTANCE.appScope.launch {
                val randomValue = Random.nextInt(0, 30_000)
                logV(randomValue)
                val dao = App.INSTANCE.mDatabase.idiomDao()
                val list = dao.getAllIdiom()
                val data = CollectionUtils.getRandList(list, 30)
                withContext(Dispatchers.Main) {
                    rv.setData(data)
                }
                loadView?.setLoadStatus(LoadingView.LOAD_SUCCESS)
            }
        } catch (e: Exception) {
            logE(e.message)
            loadView?.setLoadStatus(LoadingView.LOAD_RETRY)
        }
    }
}