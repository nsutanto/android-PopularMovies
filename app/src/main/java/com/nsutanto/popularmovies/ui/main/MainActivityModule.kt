package com.nsutanto.popularmovies.ui.main

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun bindsView(view: MainActivity): MainContract.View = view

    @Provides
    fun bindPresenter(presenter: MainPresenter): MainContract.Presenter = presenter
}