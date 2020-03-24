package com.gavin.mvvm.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gavin.mvvm.state.callback.EmptyCallBack
import com.gavin.mvvm.state.callback.ErrorCallBack
import com.gavin.mvvm.state.callback.LoadingCallBack
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
abstract class SimpleFragment :Fragment(){

    protected lateinit var loadService : LoadService<*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutID(), null)
        loadService = LoadSir.getDefault().register(rootView) {reLoad()}
        return loadService.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initEventAndData()
    }

    abstract fun initView()

    abstract fun initEventAndData()

    abstract fun getLayoutID():Int

    open fun reLoad(){
        initEventAndData()
    }


}