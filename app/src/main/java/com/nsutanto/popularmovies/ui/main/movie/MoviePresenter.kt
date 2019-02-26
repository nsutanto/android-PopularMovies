package com.nsutanto.popularmovies.ui.main.movie

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.main.MainActivityListener
import javax.inject.Inject

class MoviePresenter @Inject
constructor() :
    MovieContract.Presenter, MainActivityListener {

    override fun start() {
    }

    override fun stop() {

    }

    // Main Activity Listener
    override fun onPopularMovieUpdate(movies: List<Movie>) {

    }
}