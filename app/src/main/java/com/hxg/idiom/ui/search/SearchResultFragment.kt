package com.hxg.idiom.ui.search

import android.view.View
import com.hxg.idiom.HomeListView
import com.hxg.idiom.R

/**
 * 搜索结果页
 */
class SearchResultFragment : BaseSearchFragment() {

    override fun getLayoutRes(): Int {
        return R.layout.search_think_fragment
    }

    override fun initView(view: View) {
        super.initView(view)
    }

    override fun initRecyclerView(view: View): HomeListView? {
        return view.findViewById(R.id.recycler_view)
    }
}