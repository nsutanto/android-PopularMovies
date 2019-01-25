package com.nsutanto.popularmovies.data.api

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApiModule {
    @Provides
    @Singleton
    fun getApiSource(repo: APIRepository): ApiSource = repo
}
