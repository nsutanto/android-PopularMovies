package com.nsutanto.popularmovies.ui.main.tv

import com.nsutanto.popularmovies.data.model.TVResponse
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import javax.inject.Inject

class TVPresenter @Inject
    constructor(private val view: TVContract.View) : TVContract.Presenter {

    private var popularTV: TVResponse? = null
    private var topRatedTV: TVResponse? = null
    private var viewModel: MainViewModel? = null
    private var popularTVPage = 1
    private var topRatedTVPage = 1

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
        popularTV?.let { view.showPopularTV(popularTV!!)}
    }

    override fun onUpdatedTopRatedTV(tvResponse: TVResponse) {
        topRatedTV = tvResponse
        topRatedTV?.let { view.showTopRatedTV(topRatedTV!!)}
    }

    override fun fetchPopularTV() {
        viewModel?.getPopularTV(popularTVPage)
        popularTVPage++
    }

    override fun fetchTopRatedTV() {
        viewModel?.getTopRatedTV(topRatedTVPage)
        topRatedTVPage++
    }
}