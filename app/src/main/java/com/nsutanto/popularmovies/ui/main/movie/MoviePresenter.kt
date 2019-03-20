package com.nsutanto.popularmovies.ui.main.movie

import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import javax.inject.Inject

class MoviePresenter @Inject
    constructor(private val view: MovieContract.View) : MovieContract.Presenter {

    private var popularMovies: MovieResponse? = null
    private var topRatedMovies: MovieResponse? = null
    private var viewModel: MainViewModel? = null
    private var popularMoviesPage = 1
    private var topRatedMoviesPage = 1

    override fun create() {
        viewModel = view.getViewModel()
        fetchPopularMovies()
        fetchTopRatedMovies()
    }

    override fun start() {
        //popularMovies?.let { view.showPopularMovies(popularMovies!!) }
        //topRatedMovies?.let { view.showTopRatedMovies(topRatedMovies!!) }
    }

    override fun stop() {

    }

    override fun onUpdatedPopularMovies(movieResponse: MovieResponse) {
        popularMovies = movieResponse
        popularMovies?.let { view.showPopularMovies(popularMovies!!)}
    }

    override fun onUpdatedTopRatedMovies(movieResponse: MovieResponse) {
        topRatedMovies = movieResponse
        topRatedMovies?.let { view.showTopRatedMovies(topRatedMovies!!)}
    }

    override fun fetchPopularMovies() {
        viewModel?.getPopularMovies(popularMoviesPage)
        popularMoviesPage++
    }

    override fun fetchTopRatedMovies() {
        viewModel?.getTopRatedMovies(topRatedMoviesPage)
        topRatedMoviesPage++
    }
}