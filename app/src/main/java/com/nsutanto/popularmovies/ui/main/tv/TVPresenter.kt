package com.nsutanto.popularmovies.ui.main.tv

import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.data.model.TVResponse
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import javax.inject.Inject

class TVPresenter @Inject
    constructor(private val view: TVContract.View) : TVContract.Presenter {

    private var popularTV: TVResponse? = null
    private var topRatedTV: TVResponse? = null
    private var viewModel: MainViewModel? = null

    private var popularTVList = mutableListOf<TV>()
    private var topRatedTVList = mutableListOf<TV>()

    override fun create() {
        viewModel = view.getViewModel()
        fetchPopularTV()
        fetchTopRatedTV()
    }

    override fun start() {

    }

    override fun stop() {

    }

    override fun onUpdatedPopularTV(tvResponse: TVResponse) {
        popularTV = tvResponse
        tvResponse.results?.let { popularTVList.addAll(tvResponse.results) }
        popularTV?.let { view.showPopularTV(popularTV!!)}
    }

    override fun onUpdatedTopRatedTV(tvResponse: TVResponse) {
        topRatedTV = tvResponse
        tvResponse.results?.let { topRatedTVList.addAll(tvResponse.results) }
        topRatedTV?.let { view.showTopRatedTV(topRatedTV!!)}
    }

    override fun fetchPopularTV() {
        viewModel?.getPopularTV()
    }

    override fun fetchTopRatedTV() {
        viewModel?.getTopRatedTV()
    }

    override fun onAllPopularTVClicked() {
        view.showAllPopularTV(popularTVList)
    }

    override fun onAllTopRatedTVClicked() {
        view.showAllTopRatedTV(topRatedTVList)
    }
}