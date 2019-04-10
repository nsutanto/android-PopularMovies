package com.nsutanto.popularmovies.ui.tv_detail

import com.nsutanto.popularmovies.data.model.CreditResponse
import com.nsutanto.popularmovies.data.model.MovieVideosResponse
import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter
import com.nsutanto.popularmovies.viewmodel.TVDetailViewModel

interface TVDetailContract {

    interface View {
        fun showTVDetail(tv: TV)

        fun showVideos(videosResponse: MovieVideosResponse)

        fun showCredits(creditResponse: CreditResponse)

        fun getViewModel(): TVDetailViewModel
    }
    interface Presenter : BasePresenter {
        fun onUpdatedVideos(videosResponse: MovieVideosResponse)

        fun onUpdatedCredits(credits: CreditResponse)
    }
}