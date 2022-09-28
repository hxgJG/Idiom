package com.hxg.idiom.dialog.base

import java.util.*

object CollectionUtils {
    private const val MAXIMUM_CAPACITY = 1 shl 30

    /**
     * 提供给HashMap初始化容量时使用，HashMap的负载因子（DEFAULT_LOAD_FACTOR）默认是0.75，当前元素数量超过 n * LOAD_FACTOR，执行扩容
     * 所以如果能够确定（或大概确定）元素的个数时，可以使用该方法获取一个稳定的（或相对稳定的）容量，
     * 避免重新申请容量导致前面put过的元素重新put。
     *
     * @param initialCapacity 给定的容量，即可以确定的需要存的元素个数
     * @return 返回当前给定容量后HashMap重新扩容的最小容量（估计值）
     */
    fun getCapacity(initialCapacity: Int): Int {
        if (initialCapacity < 0) {
            throw IllegalArgumentException()
        }
        return if (initialCapacity >= MAXIMUM_CAPACITY ushr 1) MAXIMUM_CAPACITY else tableSizeFor(
            initialCapacity + (initialCapacity ushr 1) + 1
        )
    }

    /**
     * Returns a power of two table size for the given desired capacity.
     * See Hackers Delight, sec 3.2
     */
    private fun tableSizeFor(c: Int): Int {
        var n = c - 1
        n = n or (n ushr 1)
        n = n or (n ushr 2)
        n = n or (n ushr 4)
        n = n or (n ushr 8)
        n = n or (n ushr 16)
        return if (n < 0) 1 else if (n >= MAXIMUM_CAPACITY) MAXIMUM_CAPACITY else n + 1
    }

    /**
     * HashMap的负载因子: DEFAULT_LOAD_FACTOR = 0.75
     *
     * @param initialCapacity 给定的容量，即可以确定的需要存的元素个数
     * @return 返回当前给定容量后保证HashMap不会重新扩容的最小容量（准确值）
     */
    fun getMinCapacity(initialCapacity: Int): Int {
        return (initialCapacity / 0.75f + 1).toInt()
    }

    /**
     * 从集合中随机取出N个不重复的元素
     *
     * @param list 需要被取出数据的集合
     * @param n    取出的元素数量
     * @return
     */
    fun <T> getRandList(list: List<T>, n: Int): List<T> {
        val map: MutableMap<Int, String> = HashMap(getMinCapacity(n))
        val result: MutableList<T> = ArrayList()
        return if (list.size <= n) {
            list
        } else {
            while (map.size < n) {
                val random = (Math.random() * list.size).toInt()
                if (!map.containsKey(random)) {
                    map[random] = ""
                    result.add(list[random])
                }
            }
            result
        }
    }
}