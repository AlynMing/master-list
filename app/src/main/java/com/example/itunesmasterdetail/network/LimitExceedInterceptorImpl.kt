package com.example.githubuser.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LimitExceedInterceptorImpl(): LimitExceedInterceptor {

    companion object {
        const val HTTP_RESPONSE_CODE_RARE_LIMIT: Int = 403
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
         // could print response code here
        if(response.code == HTTP_RESPONSE_CODE_RARE_LIMIT)
           throw LimitExceedException()
           return response
    }
}