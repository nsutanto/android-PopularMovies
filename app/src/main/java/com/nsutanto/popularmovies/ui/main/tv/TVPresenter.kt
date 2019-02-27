package com.nsutanto.popularmovies.ui.main.tv

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.main.MainActivityListener
import javax.inject.Inject

class TVPresenter @Inject
constructor() : TVContract.Presenter, MainActivityListener {

    override fun start() {
    }

    override fun stop() {

    }

    // Main Activity Listener
    override fun displayPopularMovies(movies: List<Movie>) {

    }
}