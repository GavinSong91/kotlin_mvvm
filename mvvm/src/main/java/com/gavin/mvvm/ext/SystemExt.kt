package com.gavin.mvvm.ext

import java.lang.reflect.ParameterizedType

/**
 * Author:     gavinsong
 * Date:       2020/3/24
 * Description:
 *----------------------------------------------------------------------------------
 */

//通过反射获取子类实例
fun <T>getClass(t:Any):Class<T>{
    return (t.javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0]
            as Class<T>
}