package com.nsutanto.popularmovies.ui.main

import com.nsutanto.popularmovies.viewmodel.MainViewModel
import javax.inject.Inject

class MainPresenter
    @Inject
    constructor(private val view: MainContract.View): MainContract.Presenter {

    private var mainViewModel: MainViewModel? = null

    override fun start() {
        mainViewModel = view.getViewModel()
        mainViewModel?.getPopularMovies()
        mainViewModel?.getTopRatedMovies()
        mainViewModel?.getPopularTV()
        mainViewModel?.getTopRatedTV()
    }

    override fun stop() {
        mainViewModel?.clear()
    }

    override fun onMovieTabClicked() {
        view.displayMovieScreen()
    }

    override fun onTVTabClicked() {
        view.displayTVScreen()
    }
}