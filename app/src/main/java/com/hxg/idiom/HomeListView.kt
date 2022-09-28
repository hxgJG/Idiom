package com.hxg.idiom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hxg.idiom.db.entity.IdiomEntity

class HomeListView : RecyclerView {
    private val adapter: Adapter by lazy { Adapter() }

    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context) }

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        setLayoutManager(layoutManager)
        setAdapter(adapter)
    }

    fun setData(data: List<IdiomEntity>?) {
        adapter.setData(data)
    }

    private class Adapter : RecyclerView.Adapter<Holder>() {
        private var data: List<IdiomEntity>? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_idiom_item_layout, parent, false)
            )
        }

        @SuppressLint("NotifyDataSetChanged")
        fun setData(data: List<IdiomEntity>?) {
            this.data = data
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            if (data?.isEmpty() == true) {
                return
            }
            val idiomEntity = data!![position]
            holder.tvWord.text = idiomEntity.word
            holder.itemView.setOnClickListener {
                Router.startDetailActivity(it.context, idiomEntity)
            }
        }

        override fun getItemCount(): Int {
            return if (data == null) 0 else data!!.size
        }
    }

    private class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView = itemView.findViewById(R.id.tv_word)
    }
}