package com.hxg.idiom.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.hxg.idiom.App
import com.hxg.idiom.R
import com.hxg.idiom.dialog.base.CollectionUtils
import com.hxg.idiom.util.logE
import com.hxg.idiom.util.logV
import com.hxg.idiom.widget.LoadingView
import com.hxg.idiom.widget.SearchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * 首页-搜索
 * @author hxg
 */
class SearchActivity : AppCompatActivity() {
    private var viewPager: ViewPager2? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_search)

        viewPager = findViewById(R.id.view_pager)
        val pagerAdapter = SearchPagerAdapter(this)
        viewPager?.adapter = pagerAdapter
        viewPager?.isUserInputEnabled = false

        searchView = findViewById(R.id.search_view)
    }


}