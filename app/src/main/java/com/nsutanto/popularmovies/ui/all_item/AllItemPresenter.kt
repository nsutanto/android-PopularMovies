package com.nsutanto.popularmovies.ui.all_item

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import javax.inject.Inject

class AllItemPresenter
@Inject
constructor(private val view: AllItemContract.View) : AllItemContract.Presenter {

    private var popularMovies: MovieResponse? = null
    private var topRatedMovies: MovieResponse? = null
    private var viewModel: MainViewModel? = null

    private var popularMovieList = mutableListOf<Movie>()
    private var topRatedMovieList = mutableListOf<Movie>()

    override fun create() {
        viewModel = view.getViewModel()
    }

    override fun start() {
    }

    override fun stop() {
        viewModel?.clear()
    }

    override fun onUpdatedPopularMovies(movieResponse: MovieResponse) {
        popularMovies = movieResponse
        movieResponse.results?.let { popularMovieList.addAll(movieResponse.results) }
        popularMovies?.let { view.setItems(movieResponse.results)}
    }

    override fun onUpdatedTopRatedMovies(movieResponse: MovieResponse) {
        topRatedMovies = movieResponse
        movieResponse.results?.let { topRatedMovieList.addAll(movieResponse.results)}
        topRatedMovies?.let { view.setItems(movieResponse.results)}
    }

    override fun fetchPopularMovies() {
        viewModel?.getPopularMovies()
    }

    override fun fetchTopRatedMovies() {
        viewModel?.getTopRatedMovies()
    }
}