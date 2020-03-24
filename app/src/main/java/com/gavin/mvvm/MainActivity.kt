package com.gavin.mvvm

import com.gavin.mvvm.base.view.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {


    override fun getLayoutID(): Int =R.layout.activity_main


    override fun initView() {
        super.initView()
    }

    override fun initEventAndData() {


    }

    override fun initDateObserver() {


    }
}
