package com.gavin.mvvm.state

import androidx.annotation.StringRes

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
data class PageState constructor(var type: StateType, var msg:String="",@StringRes var tip : Int = 0)