package com.gavin.mvp.base

/**
 *  Author:Gavin
 *  Date:2019/3/13  10:34
 *  Description:This is BaseView
 */
interface BaseView {

    fun loadingview()

    fun showmainview()

    fun showerrorview(message: String?)
}