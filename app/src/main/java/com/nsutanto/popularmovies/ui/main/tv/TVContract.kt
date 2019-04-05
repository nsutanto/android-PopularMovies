package com.nsutanto.popularmovies.ui.main.tv

import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.data.model.TVResponse
import com.nsutanto.popularmovies.ui.base.presenter.BasePresenter
import com.nsutanto.popularmovies.viewmodel.MainViewModel

class TVContract {

    interface View {

        fun showPopularTV(tvResponse: TVResponse)

        fun showTopRatedTV(tvResponse: TVResponse)

        fun showAllPopularTV(tvs: List<TV>)

        fun showAllTopRatedTV(tvs: List<TV>)

        fun getViewModel(): MainViewModel
    }

    interface Presenter : BasePresenter {

        fun onUpdatedPopularTV(tvResponse: TVResponse)

        fun onUpdatedTopRatedTV(tvResponse: TVResponse)

        fun onAllPopularTVClicked()

        fun onAllTopRatedTVClicked()

        fun fetchPopularTV()

        fun fetchTopRatedTV()
    }
}