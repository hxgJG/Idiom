package com.hxg.idiom.ui.search

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class  SearchPagerAdapter(private val activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val pageMap: HashMap<Int, BaseSearchFragment> by lazy { HashMap(5) }

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): BaseSearchFragment {
        if (pageMap.containsKey(position)) {
            val fragment = pageMap[position]
            if (fragment != null) {
                return fragment
            }
        }
        val f = when (position) {
            1 -> SearchThinkFragment()
            2 -> SearchResultFragment()
            else -> SearchHomeFragment()
        }
        pageMap[position] = f
        return f
    }
}