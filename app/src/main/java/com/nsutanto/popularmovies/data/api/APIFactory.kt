package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIFactory @Inject
constructor() {

    private lateinit var client: OkHttpClient

    fun createTMDBService(): TMDBService {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AppConstants.BASE_URL)
            .client(createHttpClient())
            .build()

        return retrofit.create(TMDBService::class.java)
    }

    private fun createHttpClient(): OkHttpClient {

        client = OkHttpClient()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return client.newBuilder()
            .addInterceptor(logging)
            .addInterceptor(AuthenticationInterceptor())
            .build()
    }
}