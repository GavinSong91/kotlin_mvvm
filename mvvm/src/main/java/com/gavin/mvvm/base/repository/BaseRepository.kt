package com.gavin.mvvm.base.repository

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
open class BaseRepository {

    private val mCompositeDisposable by lazy { CompositeDisposable() }

    fun addSubscribe(disposable: Disposable) = mCompositeDisposable.add(disposable)

    fun unSubscribe() = mCompositeDisposable.dispose()
}