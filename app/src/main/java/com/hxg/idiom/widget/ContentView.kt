package com.hxg.idiom.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.hxg.idiom.R
import com.hxg.idiom.db.entity.IdiomEntity
import com.hxg.idiom.util.Util

/**
 * 成语基本信息View
 * @author hxg
 */
class ContentView : ConstraintLayout {
    private var idiomTv: TextView? = null
    private var leftParenthesis: TextView? = null
    private var pinyinTv: TextView? = null
    private var explainTv: TextView? = null
    private var fromTv: TextView? = null
    private var exampleTv: TextView? = null
    private var leftExplainMark: TextView? = null
    private var leftFromMark: TextView? = null
    private var leftExampleMark: TextView? = null

    private var dp10 = 10

    constructor(context: Context) : super(context) {
        doInit(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        doInit(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        doInit(context)
    }

    private fun doInit(ctx: Context) {
        LayoutInflater.from(ctx).inflate(R.layout.content_view_layout, this)
        idiomTv = findViewById(R.id.idiom_tv)

        leftParenthesis = findViewById(R.id.left_parenthesis)
        pinyinTv = findViewById(R.id.pinyin_tv)

        leftExplainMark = findViewById(R.id.left_explain_mark)
        explainTv = findViewById(R.id.explain_tv)

        leftFromMark = findViewById(R.id.left_from_mark)
        fromTv = findViewById(R.id.from_tv)

        leftExampleMark = findViewById(R.id.left_example_mark)
        exampleTv = findViewById(R.id.example_tv)

        dp10 = Util.dpToPx(10f, ctx)
    }

    @SuppressLint("SetTextI18n")
    fun updateUI(entity: IdiomEntity) {
        val wordView = idiomTv ?: return
        visibility = VISIBLE
        wordView.text = entity.word ?: ""

        val py = entity.pinyin
        if (py.isNullOrEmpty()) {
            leftParenthesis?.visibility = GONE
        } else {
            var isLeftShow = false;

            leftParenthesis?.let { lp ->
                val text: CharSequence? = lp.text
                if (text == null || text.isEmpty()) {
                    lp.visibility = GONE
                    return@let
                }
                isLeftShow = true
                lp.visibility = VISIBLE
                val ss = SpannableString(text)
                ss.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    text.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                lp.text = ss
            }

            val ssb = SpannableStringBuilder()
            var start = 0
            ssb.setSpan(
                StyleSpan(Typeface.BOLD),
                start,
                ssb.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            ssb.append(py)
            if (isLeftShow) {
                start = ssb.length
                ssb.append("）")
                ssb.setSpan(
                    StyleSpan(Typeface.BOLD),
                    start,
                    ssb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            pinyinTv?.text = ssb
        }

        setContent(leftExplainMark, explainTv, R.string.explain, entity.explain ?: "无")
        setContent(leftFromMark, fromTv, R.string.from, entity.from ?: "未知")
        setContent(leftExampleMark, exampleTv, R.string.example, entity.example ?: "")
    }

    private fun setContent(leftMark: TextView?, tv: TextView?, headResId: Int, content: String) {
        if (tv == null) {
            leftMark?.visibility = GONE
            return
        }

        if (content.isEmpty()) {
            leftMark?.visibility = GONE
            tv.visibility = GONE
        } else {
            leftMark?.visibility = VISIBLE
            tv.visibility = VISIBLE
            tv.text = String.format("%s%s", context.getString(headResId), content)
        }
    }
}