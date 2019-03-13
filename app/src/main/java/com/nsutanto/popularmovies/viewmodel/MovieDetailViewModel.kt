package com.nsutanto.popularmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsutanto.popularmovies.data.api.ApiSource
import com.nsutanto.popularmovies.data.model.CreditResponse
import com.nsutanto.popularmovies.data.model.MovieVideosResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class MovieDetailViewModel
    constructor(private val api: ApiSource,
                private val id: Int): ViewModel() {
    private var apiRequest = CompositeDisposable()

    val videos = MutableLiveData<MovieVideosResponse>()
    val credits = MutableLiveData<CreditResponse>()

    fun clear() {
        apiRequest.clear()
    }

    fun getMovieVideos() {

        apiRequest.add(api.getMovieVideos(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { v -> handleGetMovieVideos(v) },
                { e -> handleNetworkError(e) }))
    }

    fun getMovieCredits() {

        apiRequest.add(api.getMovieCredit(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { c -> handleGetMovieCredits(c) },
                { e -> handleNetworkError(e) }))
    }

    private fun handleGetMovieVideos(videos: MovieVideosResponse) {
        this.videos.value = videos
    }

    private fun handleGetMovieCredits(credits: CreditResponse) {
        this.credits.value = credits
    }

    private fun handleNetworkError(e: Throwable) {
        Timber.e(e)
    }
}