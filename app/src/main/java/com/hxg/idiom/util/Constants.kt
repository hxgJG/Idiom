package com.hxg.idiom.util

object Constants {
    // 初始化数据库状态，只初始化一次
    const val KEY_INIT_DATA_STATE = "init_data_state"

    // 数据库初始化完成状态
    const val VALUE_INIT_DATA_STATE = "initialized"

    //------------------------ intent传参相关 ------------------------------
    // 成语的id
    const val INTENT_ID = "INTENT_ID"

    // 成语的信息entity
    const val INTENT_ENTITY = "INTENT_ENTITY"

    //------------------------ 空格 ------------------------------
    // 半角空格 (160)
    const val HALF_WIDTH_SPACE = 160.toChar().toString()
    // 全角空格 (12288)
    const val FULL_WIDTH_SPACE = 12288.toChar().toString()
}