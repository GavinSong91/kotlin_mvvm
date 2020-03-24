package com.gavin.mvvm.state.callback

import com.gavin.mvvm.R
import com.kingja.loadsir.callback.Callback

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
class EmptyCallBack:Callback()  {
    override fun onCreateView(): Int = R.layout.widget_pageloading
}