package com.gavin.mvp.base

/**
 *  Author:Gavin
 *  Date:2019/3/13  10:34
 *  Description:This is BasePresenter
 */
interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)
    fun detachView()
}