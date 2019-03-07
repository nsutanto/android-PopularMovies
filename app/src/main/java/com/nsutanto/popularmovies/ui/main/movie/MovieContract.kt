package com.nsutanto.popularmovies.ui.main.movie

import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter

class MovieContract {

    interface View {

        fun showPopularMovies(movieResponse: MovieResponse)

        fun showTopRatedMovies(movieResponse: MovieResponse)
    }

    interface Presenter : BasePresenter {

        fun onUpdatedPopularMovies(movieResponse: MovieResponse)

        fun onUpdatedTopRatedMovies(movieResponse: MovieResponse)
    }
}