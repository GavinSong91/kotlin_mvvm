package com.gavin.mvvm.base.app

import android.app.Application
import com.gavin.mvvm.state.callback.EmptyCallBack
import com.gavin.mvvm.state.callback.ErrorCallBack
import com.gavin.mvvm.state.callback.LoadingCallBack
import com.kingja.loadsir.core.LoadSir

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        LoadSir.beginBuilder()
            .addCallback(EmptyCallBack())
            .addCallback(ErrorCallBack())
            .addCallback(LoadingCallBack())
            .commit()
    }
}