package com.gavin.mvvm.base.view

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
interface BaseView {

    /**
     * 显示内容
     */
    fun showContent()

    /**
     * 显示加载提示
     */
    fun showLoading()

    /**
     * 显示空页面
     */
    fun showEmpty()

    /**
     * 显示提示
     * */
    fun showTip(msg: String)

    /**
     * 刷新失败
     */
    fun showError(msg: String?)
}