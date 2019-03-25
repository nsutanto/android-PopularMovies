package com.nsutanto.popularmovies.ui.main

import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter
import com.nsutanto.popularmovies.viewmodel.MainViewModel

interface MainContract {
    interface View {
        fun displayMovieScreen()

        fun displayTVScreen()

        fun displayAllPopularMovie()

        fun getViewModel(): MainViewModel
    }

    interface Presenter : BasePresenter {
        fun onMovieTabClicked()

        fun onTVTabClicked()

        fun onAllPopularMovieClicked()
    }
}

