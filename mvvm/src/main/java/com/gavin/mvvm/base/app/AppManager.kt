package com.gavin.mvvm.base.app

import android.app.Activity
import java.util.*
import kotlin.system.exitProcess

/**
 * Author:     gavinsong
 * Date:       2020/3/22
 * Description:
 *----------------------------------------------------------------------------------
 */
class AppManager private constructor(){
    private var activityStack: Stack<Activity>? = null

    companion object{
        var instance: AppManager = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder= AppManager()
    }


    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack.isNullOrEmpty()) {
            activityStack = Stack()
        }
        activityStack?.add(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity) {
        activityStack?.remove(activity)
        activity.finish()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        var activity = activityStack?.lastElement()
        activity?.finish()
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        activityStack?.forEach {
            if (it.javaClass.equals(cls)) {
                finishActivity(it)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        activityStack?.forEach {
            it.finish()
        }
        activityStack?.clear()
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack?.lastElement()
    }

    fun isExist(): Boolean {
        return activityStack!!.size > 1
    }

    fun isExist(cls: Class<*>): Boolean {
        var isExist: Boolean = false
        activityStack?.forEach {
            if (it.javaClass.name.equals(cls.name)) {
                isExist = true
                return@forEach
            }
        }
        return isExist
    }

    fun astack(): Stack<Activity> {
        return activityStack!!
    }


    /**
     * 退出应用程序
     */
    fun appExit() {
        try {
            finishAllActivity()
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}