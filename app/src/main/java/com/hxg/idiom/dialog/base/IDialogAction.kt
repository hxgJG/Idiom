package com.hxg.idiom.dialog.base

/**
 * 对话框行为接口
 *
 * @author hxg
 */
interface IDialogAction {
    /**
     * 显示对话框
     */
    fun showDialog()

    /**
     * 隐藏对话框
     */
    fun dismissDialog()
}