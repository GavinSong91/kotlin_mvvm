package com.gavin.mvp.base


/**
 *  Author:Gavin
 *  Date:2019/3/13  10:33
 *  Description:This is BaseActivity
 */
abstract class BaseActivity<in V : BaseView, P : BasePresenter<V>> : SimpleActivity(), BaseView {

    protected var mPresenter: P? = createPresenter()

    protected abstract fun createPresenter(): P

    override fun onViewCreated() {
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


}