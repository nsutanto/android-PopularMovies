package com.nsutanto.popularmovies.ui.all_item

import com.nsutanto.popularmovies.utils.AppConstants
import dagger.Module
import dagger.Provides

@Module
class AllItemActivityModule {
    @Provides
    fun bindsView(view: AllItemActivity): AllItemContract.View = view

    @Provides
    fun bindPresenter(presenter: AllItemPresenter): AllItemContract.Presenter = presenter

    @Provides
    fun bindItemType(activity: AllItemActivity): AppConstants.AllItemType {
        return activity.intent.getSerializableExtra(AppConstants.ALL_ITEM_TYPE_INTENT) as AppConstants.AllItemType
    }

}