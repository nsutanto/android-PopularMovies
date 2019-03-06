package com.nsutanto.popularmovies.ui.main.tv

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter

class TVContract {

    interface View {
        fun displayPopularTV(movies: List<Movie>?)

        fun displayTopRatedTV(movies: List<Movie>?)
    }

    interface Presenter : BasePresenter {
        fun onUpdatePopularTV(movies: List<Movie>?)

        fun onUpdateTopRatedTV(movies: List<Movie>?)
    }
}