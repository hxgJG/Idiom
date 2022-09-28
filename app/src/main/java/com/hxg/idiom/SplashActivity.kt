package com.hxg.idiom

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hxg.idiom.ui.search.SearchActivity
import com.hxg.idiom.util.SpCache

/**
 * 欢迎页
 * @author hxg
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val img = findViewById<View>(R.id.img)
        img.postDelayed(
            { startActivity(Intent(this@SplashActivity, SearchActivity::class.java)) },
            if (SpCache.getInstance().isInitialized()) 1000 else 5000
        )
    }
}