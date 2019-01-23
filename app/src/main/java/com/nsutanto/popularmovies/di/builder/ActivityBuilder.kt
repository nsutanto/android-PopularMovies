package com.nsutanto.popularmovies.di.builder

import com.nsutanto.popularmovies.ui.main.MainActivity
import com.nsutanto.popularmovies.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity
}