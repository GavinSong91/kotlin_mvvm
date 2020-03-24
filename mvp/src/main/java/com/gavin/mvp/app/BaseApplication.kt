package com.gavin.mvp.app

import android.app.Application
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
            .commit()
    }
}