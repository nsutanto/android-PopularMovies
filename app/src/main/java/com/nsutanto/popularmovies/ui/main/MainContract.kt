package com.nsutanto.popularmovies.ui.main

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter

interface MainContract {
    interface View {
        fun displayMovies(movies: List<Movie>)
    }

    interface Presenter : BasePresenter
}

