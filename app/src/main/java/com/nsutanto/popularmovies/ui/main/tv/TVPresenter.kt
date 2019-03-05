package com.nsutanto.popularmovies.ui.main.tv

import com.nsutanto.popularmovies.data.model.Movie
import javax.inject.Inject

class TVPresenter @Inject
constructor(private val view: TVContract.View) : TVContract.Presenter {

    private var popularTV: List<Movie>? = null
    private var topRatedTV: List<Movie>? = null

    override fun start() {
        view.displayPopularTV(popularTV)
        view.displayTopRatedTV(topRatedTV)
    }

    override fun stop() {

    }

    override fun onUpdatePopularTV(movies: List<Movie>?) {
        popularTV = movies
        view.displayPopularTV(popularTV)
    }

    override fun onUpdateTopRatedTV(movies: List<Movie>?) {
        topRatedTV = movies
        view.displayTopRatedTV(topRatedTV)
    }
}