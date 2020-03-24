package com.gavin.mvvm.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gavin.mvvm.base.repository.BaseRepository
import com.gavin.mvvm.ext.getClass
import com.gavin.mvvm.state.PageState

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
abstract class BaseViewModel <M : BaseRepository>(application : Application): AndroidViewModel(application) {

    val loadState by lazy {
        MutableLiveData<PageState>()
    }

    val respository:M by lazy {
        // 获取对应Repository 实例 (有参构造函数)
        (getClass<M>(this))
            // 获取构造函数的构造器，传入参数class
            .getDeclaredConstructor(MutableLiveData::class.java)
            // 传入加载状态
            .newInstance(loadState)
    }

    override fun onCleared() {
        super.onCleared()
        respository.unSubscribe()
    }
}