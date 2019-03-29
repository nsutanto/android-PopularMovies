package com.nsutanto.popularmovies.ui.main.movie

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter
import com.nsutanto.popularmovies.viewmodel.MainViewModel

class MovieContract {

    interface View {

        fun showPopularMovies(movieResponse: MovieResponse)

        fun showTopRatedMovies(movieResponse: MovieResponse)

        fun showAllPopularMovies(movies: List<Movie>)

        fun getViewModel(): MainViewModel
    }

    interface Presenter : BasePresenter {

        fun onUpdatedPopularMovies(movieResponse: MovieResponse)

        fun onUpdatedTopRatedMovies(movieResponse: MovieResponse)

        fun onAllPopularMovieClicked()

        fun fetchPopularMovies()

        fun fetchTopRatedMovies()
    }
}