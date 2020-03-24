package com.gavin.mvvm.base.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gavin.mvvm.base.viewmodel.BaseViewModel
import com.gavin.mvvm.ext.getClass
import com.gavin.mvvm.state.PageState
import com.gavin.mvvm.state.StateType
import com.gavin.mvvm.state.callback.EmptyCallBack
import com.gavin.mvvm.state.callback.ErrorCallBack
import com.gavin.mvvm.state.callback.LoadingCallBack
import com.kingja.loadsir.callback.SuccessCallback

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
abstract class BaseFragment<VM:BaseViewModel<*>>:SimpleFragment(),BaseView {

    protected lateinit var mViewModel: VM

    override fun initView() {
        mViewModel = ViewModelProviders.of(this).get(getClass(this))
        mViewModel.loadState.observe(this, observer)
        initDataObserver()
    }

    abstract fun initDataObserver()

    //应用状态
    private val observer by lazy {
        Observer<PageState> {
            it?.let {
                when (it.type) {
                    StateType.SUCCESS -> showContent()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showTip(it.msg)
                    StateType.NETWORK_ERROR -> showError("网络异常,点击重新加载试试~")
                    StateType.TIP -> showTip(it.msg)
                    StateType.EMPTY -> showEmpty()
                }
            }
        }
    }

    override fun showContent() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    override fun showLoading() {
        loadService.showCallback(LoadingCallBack::class.java)
    }

    override fun showEmpty() {
        loadService.showCallback(EmptyCallBack::class.java)
    }

    override fun showTip(msg: String) {
        msg?.let {
            //显示toast
        }
        loadService.showCallback(SuccessCallback::class.java)
    }

    override fun showError(msg: String?) {
        msg?.let {
            //显示toast
        }
        loadService.showCallback(ErrorCallBack::class.java)
    }

    override fun reLoad() {
        super.reLoad()
        showLoading()
    }
}