package com.gavin.mvp.base

import io.reactivex.disposables.Disposable

/**
 *  Author:Gavin
 *  Date:2019/3/14  10:23
 *  Description:This is BaseModel
 */
interface BaseModel {

    fun addDisposable(disposable: Disposable?)

    fun onDetach()
}