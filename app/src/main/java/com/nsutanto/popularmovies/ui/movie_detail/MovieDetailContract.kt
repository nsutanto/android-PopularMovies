package com.nsutanto.popularmovies.ui.movie_detail

import com.nsutanto.popularmovies.data.model.CreditResponse
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.MovieVideosResponse
import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter
import com.nsutanto.popularmovies.viewmodel.MovieDetailViewModel

interface MovieDetailContract {

    interface View {
        fun showMovieDetail(movie: Movie)

        fun showVideos(videosResponse: MovieVideosResponse)

        fun showCredits(creditResponse: CreditResponse)

        fun getViewModel(): MovieDetailViewModel
    }
    interface Presenter : BasePresenter {
        fun onUpdatedVideos(videosResponse: MovieVideosResponse)

        fun onUpdatedCredits(credits: CreditResponse)
    }
}