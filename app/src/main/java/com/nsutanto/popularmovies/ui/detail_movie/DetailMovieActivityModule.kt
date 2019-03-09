package com.nsutanto.popularmovies.ui.detail_movie

import dagger.Module
import dagger.Provides

@Module
class DetailMovieActivityModule {
    @Provides
    fun bindsView(view: DetailMovieActivity): DetailMovieContract.View = view

    @Provides
    fun bindPresenter(presenter: DetailMoviePresenter): DetailMovieContract.Presenter = presenter
}