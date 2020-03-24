package com.gavin.mvvm.base.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gavin.mvvm.R
import com.gavin.mvvm.base.app.AppManager
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */
abstract class SimpleActivity: AppCompatActivity() {

    lateinit var rootView:View

    val loadService : LoadService<*> by lazy {
        LoadSir.getDefault().register(this) {
            reLoad()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        rootView = (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0)
        AppManager.instance.addActivity(this)
        initView()
        initEventAndData()
    }

    abstract fun initView()

    abstract fun initEventAndData()

    abstract fun getLayoutID():Int

    open fun reLoad(){}

    /**
     * 设置标题栏
     * */
    open fun setToolBar(toolbar: Toolbar, title: String){
        toolbar.title = title
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out)
    }
}