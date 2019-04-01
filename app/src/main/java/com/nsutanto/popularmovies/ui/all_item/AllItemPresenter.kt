package com.nsutanto.popularmovies.ui.all_item

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.data.model.TVResponse
import com.nsutanto.popularmovies.utils.AppConstants
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import javax.inject.Inject

class AllItemPresenter
@Inject
constructor(private val view: AllItemContract.View,
            private val itemType: AppConstants.AllItemType) : AllItemContract.Presenter {

    private var popularMovies: MovieResponse? = null
    private var topRatedMovies: MovieResponse? = null
    private var popularTV: TVResponse? = null
    private var topRatedTV: TVResponse? = null

    private var viewModel: MainViewModel? = null

    private var popularMovieList = mutableListOf<Movie>()
    private var topRatedMovieList = mutableListOf<Movie>()
    private var popularTVList = mutableListOf<TV>()
    private var topRatedTVList = mutableListOf<TV>()

    override fun create() {
        viewModel = view.getViewModel()

        fetchData()
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

    override fun onUpdatedPopularTV(tvResponse: TVResponse) {
        popularTV = tvResponse
        tvResponse.results?.let { popularTVList.addAll(tvResponse.results) }
        popularTV?.let { view.setItems(tvResponse.results)}
    }

    override fun onUpdatedTopRatedTV(tvResponse: TVResponse) {
        topRatedTV = tvResponse
        tvResponse.results?.let { topRatedTVList.addAll(tvResponse.results) }
        topRatedTV?.let { view.setItems(tvResponse.results)}
    }

    override fun fetchPopularMovies() {
        viewModel?.getPopularMovies()
    }

    override fun fetchTopRatedMovies() {
        viewModel?.getTopRatedMovies()
    }

    override fun fetchPopularTV() {
        viewModel?.getPopularTV()
    }

    override fun fetchTopRatedTV() {
        viewModel?.getTopRatedTV()
    }

    override fun fetchData() {
        when (itemType) {
            AppConstants.AllItemType.POPULAR_MOVIE -> fetchPopularMovies()
            AppConstants.AllItemType.TOP_RATED_MOVIE -> fetchTopRatedMovies()
            AppConstants.AllItemType.POPULAR_TV -> fetchPopularTV()
            AppConstants.AllItemType.TOP_RATED_TV -> fetchTopRatedTV()
        }
    }
}