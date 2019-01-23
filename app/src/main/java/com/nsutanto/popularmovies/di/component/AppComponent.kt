package com.nsutanto.popularmovies.di.component

import android.app.Application
import com.nsutanto.popularmovies.PopularMoviesApp
import com.nsutanto.popularmovies.data.api.ApiModule
import com.nsutanto.popularmovies.data.api.ApiSource
import com.nsutanto.popularmovies.di.builder.ActivityBuilder
import com.nsutanto.popularmovies.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class), (ApiModule::class)])
interface AppComponent {
    fun apiSource(): ApiSource

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

        fun apiModule(module: ApiModule): Builder
    }

    fun inject(app: PopularMoviesApp)
}