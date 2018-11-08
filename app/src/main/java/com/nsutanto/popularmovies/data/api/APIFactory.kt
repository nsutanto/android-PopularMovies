package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class APIFactory {

    fun createTMDBService(): TMDBService {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AppConstants.BASE_URL)
            .build()

        return retrofit.create(TMDBService::class.java);
    }
}