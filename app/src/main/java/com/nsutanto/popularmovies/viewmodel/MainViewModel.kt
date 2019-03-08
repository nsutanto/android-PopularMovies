package com.nsutanto.popularmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsutanto.popularmovies.data.api.ApiSource
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.data.model.TVResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class MainViewModel
    constructor(private val api: ApiSource): ViewModel() {
    private var apiRequest = CompositeDisposable()

    val popularMovies = MutableLiveData<MovieResponse>()
    val topRatedMovies = MutableLiveData<MovieResponse>()
    val popularTV = MutableLiveData<TVResponse>()
    val topRatedTV = MutableLiveData<TVResponse>()

    fun clear() {
        apiRequest.clear()
    }

    fun getPopularMovies() {
        apiRequest.add(api.getPopularMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { m -> handleGetPopularMovies(m) },
                { e -> handleNetworkError(e) }))
    }

    fun getTopRatedMovies() {
        apiRequest.add(api.getTopRatedMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { m -> handleGetTopRatedMovies(m) },
                { e -> handleNetworkError(e) }))
    }

    fun getPopularTV() {
        apiRequest.add(api.getPopularTV()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { m -> handleGetPopularTV(m) },
                { e -> handleNetworkError(e) }))
    }

    fun getTopRatedTV() {
        apiRequest.add(api.getTopRatedTV()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { m -> handleGetTopRatedTV(m) },
                { e -> handleNetworkError(e) }))
    }

    private fun handleGetPopularMovies(movieResponse: MovieResponse) {
        popularMovies.value = movieResponse
    }

    private fun handleGetTopRatedMovies(movieResponse: MovieResponse) {
        topRatedMovies.value = movieResponse
    }

    private fun handleGetPopularTV(tvResponse: TVResponse) {
        popularTV.value = tvResponse
    }

    private fun handleGetTopRatedTV(tvResponse: TVResponse) {
        topRatedTV.value = tvResponse
    }

    private fun handleNetworkError(e: Throwable) {
        Timber.e(e)
    }
}