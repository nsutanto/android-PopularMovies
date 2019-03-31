package com.nsutanto.popularmovies.ui.all_item

import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter
import com.nsutanto.popularmovies.viewmodel.MainViewModel

interface AllItemContract {

    interface View {
        fun getViewModel() : MainViewModel

        fun setItems(objects: List<Any>?)
    }

    interface Presenter : BasePresenter {

        fun onUpdatedPopularMovies(movieResponse: MovieResponse)

        fun onUpdatedTopRatedMovies(movieResponse: MovieResponse)

        fun fetchPopularMovies()

        fun fetchTopRatedMovies()
    }
}