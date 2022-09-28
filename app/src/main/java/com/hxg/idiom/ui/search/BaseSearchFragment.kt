package com.hxg.idiom.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.hxg.idiom.HomeListView

/**
 * 搜索fragment基类
 */
abstract class BaseSearchFragment : Fragment() {
    protected var recyclerView: HomeListView? = null

    override fun onCreateView(li: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        val view = li.inflate(getLayoutRes(), container, false)
        recyclerView = initRecyclerView(view)
        initView(view)
        return view
    }

    protected open fun initView(view: View) {
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun initRecyclerView(view: View): HomeListView?
}