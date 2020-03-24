package com.gavin.network.interceptor

import com.gavin.network.HttpConstant
import okhttp3.Interceptor
import okhttp3.Response

/**
 *  Author:Gavin
 *  Date:2019/3/13  15:52
 *  Description:This is SaveCookieInterceptor
 */
class SaveCookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestUrl = request.url().toString()
        val domain = request.url().host()

        // set-cookie maybe has multi, login to save cookie
        if ((requestUrl.contains(HttpConstant.SAVE_USER_LOGIN_KEY) || requestUrl.contains(
                HttpConstant.SAVE_USER_REGISTER_KEY))
                && !response.headers(HttpConstant.SET_COOKIE_KEY).isEmpty()) {

            val cookies = response.headers(HttpConstant.SET_COOKIE_KEY)
            val cookie = HttpConstant.encodeCookie(cookies)
            HttpConstant.saveCookie(requestUrl, domain, cookie)

        }
        return response
    }
}