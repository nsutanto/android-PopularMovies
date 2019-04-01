package com.nsutanto.popularmovies.ui.all_item

import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.data.model.TVResponse
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

        fun onUpdatedPopularTV(tvResponse: TVResponse)

        fun onUpdatedTopRatedTV(tvResponse: TVResponse)

        fun fetchPopularMovies()

        fun fetchTopRatedMovies()

        fun fetchPopularTV()

        fun fetchTopRatedTV()

        fun fetchData()
    }
}