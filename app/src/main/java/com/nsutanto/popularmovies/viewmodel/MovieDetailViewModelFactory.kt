package com.nsutanto.popularmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nsutanto.popularmovies.data.api.ApiSource
import javax.inject.Inject

class MovieDetailViewModelFactory
    @Inject
    constructor(private val api: ApiSource,
                private val id: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(api, id) as T
    }
}