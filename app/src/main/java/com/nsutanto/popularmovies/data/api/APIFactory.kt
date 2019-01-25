package com.nsutanto.popularmovies.data.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
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

    fun createTMDBService(): TMDBService {

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(AppConstants.BASE_URL)
            .client(createHttpClient())
            .build()

        return retrofit.create(TMDBService::class.java)
    }

    private fun createHttpClient(): OkHttpClient {

        val client = OkHttpClient()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return client.newBuilder()
            .addInterceptor(logging)
            .addInterceptor(AuthenticationInterceptor())
            .build()
    }
}