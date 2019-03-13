package com.nsutanto.popularmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nsutanto.popularmovies.data.api.ApiSource
import javax.inject.Inject

class MainViewModelFactory
    @Inject
    constructor(private val api: ApiSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(api) as T
    }
}