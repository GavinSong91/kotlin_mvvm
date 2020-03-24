package com.xinyi.baselib.network.response

import com.squareup.moshi.Json

/**
 *  Author:Gavin
 *  Date:2019/3/13  16:54
 *  Description:This is HttpResponse
 */
class HttpResponse<T>(
        val message: String,
        val code: String,
        @Json(name = "data")
        val data: T) {


    override fun toString(): String {
        return "HttpResponse(message=$message, code=$code, data=$data)"
    }
}