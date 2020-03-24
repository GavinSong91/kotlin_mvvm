package com.gavin.mvp.base

import android.view.View

/**
 *  Author:Gavin
 *  Date:2019/3/13  14:40
 *  Description:This is BaseFragment
 */
abstract class BaseFragment<in V : BaseView, P : BasePresenter<V>> : SimpleFragment(), BaseView {

    var mPresenter: P? = createPresenter()

    abstract fun createPresenter(): P

    override fun initView(view: View) {
        if (mPresenter != null) {
            mPresenter?.attachView(this as V)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter?.detachView()
            this.mPresenter = null
        }
    }

    override fun loadingview() {
    }

    override fun showmainview() {

    }

    override fun showerrorview(message: String?) {
    }

    override fun onFragmentResume() {

    }

    override fun onFragmentPause() {

    }
}