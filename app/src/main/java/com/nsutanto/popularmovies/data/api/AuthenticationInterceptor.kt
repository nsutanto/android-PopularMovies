package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.utils.AppConstants.API_KEY
import com.nsutanto.popularmovies.utils.AppConstants.API_KEY_VALUE
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(API_KEY, API_KEY_VALUE).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}