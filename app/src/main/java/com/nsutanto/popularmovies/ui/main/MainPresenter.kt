package com.nsutanto.popularmovies.ui.main

import com.nsutanto.popularmovies.viewmodel.MainViewModel
import javax.inject.Inject

class MainPresenter
    @Inject
    constructor(private val view: MainContract.View): MainContract.Presenter {

    private var viewModel: MainViewModel? = null

    override fun create() {
        viewModel = view.getViewModel()
    }

    override fun start() {

    }

    override fun stop() {
        viewModel?.clear()
    }

    override fun onMovieTabClicked() {
        view.displayMovieScreen()
    }

    override fun onTVTabClicked() {
        view.displayTVScreen()
    }

    override fun onAllPopularMovieClicked() {
        view.displayAllPopularMovie()
    }
}