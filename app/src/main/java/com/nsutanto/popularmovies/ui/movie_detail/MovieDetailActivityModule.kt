package com.nsutanto.popularmovies.ui.movie_detail

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.utils.AppConstants.MOVIE_INTENT
import dagger.Module
import dagger.Provides

@Module
class MovieDetailActivityModule {
    @Provides
    fun bindsView(view: MovieDetailActivity): MovieDetailContract.View = view

    @Provides
    fun bindPresenter(presenter: MovieDetailPresenter): MovieDetailContract.Presenter = presenter

    @Provides
    fun provideMovie(activity: MovieDetailActivity): Movie {
        return activity.intent.getParcelableExtra(MOVIE_INTENT)
    }

    @Provides
    fun provideMovieId(activity: MovieDetailActivity): Int {
        val movie = activity.intent.getParcelableExtra(MOVIE_INTENT) as Movie
        return movie.id!!
    }
}