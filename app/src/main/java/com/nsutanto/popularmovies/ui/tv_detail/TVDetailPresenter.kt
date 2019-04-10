package com.nsutanto.popularmovies.ui.tv_detail

import com.nsutanto.popularmovies.data.model.CreditResponse
import com.nsutanto.popularmovies.data.model.MovieVideosResponse
import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.viewmodel.TVDetailViewModel
import javax.inject.Inject

class TVDetailPresenter
    @Inject
    constructor(private val view: TVDetailContract.View,
                private val tv: TV
    ) : TVDetailContract.Presenter {

    private var viewModel: TVDetailViewModel? = null

    override fun create() {

    }

    override fun start() {
        viewModel = view.getViewModel()
        viewModel?.getTVVideos()
        viewModel?.getTVCredits()

        view.showTVDetail(tv)
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