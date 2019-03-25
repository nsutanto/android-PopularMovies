package com.nsutanto.popularmovies.ui.all_item

import dagger.Module
import dagger.Provides

@Module
class AllItemActivityModule {
    @Provides
    fun bindsView(view: AllItemActivity): AllItemContract.View = view

    @Provides
    fun bindPresenter(presenter: AllItemPresenter): AllItemContract.Presenter = presenter

}