package com.gavin.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 *  Author:Gavin
 *  Date:2019/3/13  15:55
 *  Description:This is HeaderInterceptor
 */
class HeaderInterceptor : Interceptor {

    /**
     * token
     */
    private var token: String =""

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-type", "application/json")
         .header("userId", token)
         .method(request.method(), request.body())

        val domain = request.url().host()
        val url = request.url().toString()
        if (domain.isNotEmpty()) {
//            val spDomain: String by Preference(domain, "")
//            val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
//            if (cookie.isNotEmpty()) {
//                // 将 Cookie 添加到请求头
//                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
//            }
        }

        return chain.proceed(builder.build())
    }
}