package com.nsutanto.popularmovies.ui.tv_detail

import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.utils.AppConstants.TV_INTENT
import dagger.Module
import dagger.Provides

@Module
class TVDetailActivityModule {
    @Provides
    fun bindsView(view: TVDetailActivity): TVDetailContract.View = view

    @Provides
    fun bindPresenter(presenter: TVDetailPresenter): TVDetailContract.Presenter = presenter

    @Provides
    fun provideTv(activity: TVDetailActivity): TV {
        return activity.intent.getParcelableExtra(TV_INTENT)
    }

    @Provides
    fun provideMovieId(activity: TVDetailActivity): Int {
        val tv = activity.intent.getParcelableExtra(TV_INTENT) as TV
        return tv.id!!
    }
}