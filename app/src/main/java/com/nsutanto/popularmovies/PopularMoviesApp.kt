package com.nsutanto.popularmovies

import android.app.Activity
import android.app.Application
import com.nsutanto.popularmovies.di.component.AppComponent
import com.nsutanto.popularmovies.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class PopularMoviesApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var component: AppComponent

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .application(this)
            .build()

        component.inject(this)
    }

    fun getApplicationComponent(): AppComponent = component
}