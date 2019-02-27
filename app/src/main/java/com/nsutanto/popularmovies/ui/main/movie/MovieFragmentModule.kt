package com.nsutanto.popularmovies.ui.main.movie

import dagger.Module
import dagger.Provides

@Module
class MovieFragmentModule {

    @Provides
    fun bindsView(view: MovieFragment): MovieContract.View = view

    @Provides
    fun bindsPresenter(presenter: MoviePresenter): MovieContract.Presenter = presenter
}