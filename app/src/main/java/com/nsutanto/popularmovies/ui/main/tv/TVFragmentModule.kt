package com.nsutanto.popularmovies.ui.main.tv

import dagger.Module
import dagger.Provides

@Module
class TVFragmentModule {

    @Provides
    fun bindsView(view: TVFragment): TVContract.View = view

    @Provides
    fun bindsPresenter(presenter: TVPresenter): TVContract.Presenter = presenter
}