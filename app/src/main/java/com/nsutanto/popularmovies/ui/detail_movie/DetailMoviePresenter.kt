package com.nsutanto.popularmovies.ui.detail_movie

import javax.inject.Inject

class DetailMoviePresenter
@Inject
constructor(private val view: DetailMovieContract.View)
    : DetailMovieContract.Presenter {

    //private var mainViewModel: MainViewModel? = null

    override fun start() {
        //mainViewModel = view.getViewModel()
        //mainViewModel?.getPopularMovies()
        //mainViewModel?.getTopRatedMovies()
        //mainViewModel?.getPopularTV()
        //mainViewModel?.getTopRatedTV()
    }

    override fun stop() {
        //mainViewModel?.clear()
    }
}