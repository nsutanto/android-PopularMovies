package com.nsutanto.popularmovies.ui.movie_detail

import com.nsutanto.popularmovies.data.model.CreditResponse
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.MovieVideosResponse
import com.nsutanto.popularmovies.viewmodel.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailPresenter
    @Inject
    constructor(private val view: MovieDetailContract.View,
            private val movie: Movie) : MovieDetailContract.Presenter {

    private var viewModel: MovieDetailViewModel? = null

    override fun create() {

    }

    override fun start() {
        viewModel = view.getViewModel()
        viewModel?.getMovieVideos()
        viewModel?.getMovieCredits()

        view.showMovieDetail(movie)
    }

    override fun stop() {
        viewModel?.clear()
    }

    override fun onUpdatedVideos(videosResponse: MovieVideosResponse) {
        view.showVideos(videosResponse)
    }

    override fun onUpdatedCredits(credits: CreditResponse) {
        view.showCredits(credits)
    }
}