package com.hxg.idiom.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hxg.idiom.HomeListView
import com.hxg.idiom.R

/**
 * 搜索联想页
 */
class SearchThinkFragment : BaseSearchFragment() {

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