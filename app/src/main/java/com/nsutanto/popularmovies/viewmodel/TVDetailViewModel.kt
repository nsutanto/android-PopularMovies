package com.nsutanto.popularmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsutanto.popularmovies.data.api.ApiSource
import com.nsutanto.popularmovies.data.model.CreditResponse
import com.nsutanto.popularmovies.data.model.MovieVideosResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class TVDetailViewModel
constructor(private val api: ApiSource,
            private val id: Int): ViewModel() {
    private var apiRequest = CompositeDisposable()

    val videos = MutableLiveData<MovieVideosResponse>()
    val credits = MutableLiveData<CreditResponse>()

    fun clear() {
        apiRequest.clear()
    }

    fun getTVVideos() {

        apiRequest.add(api.getTVVideos(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { v -> handleGetTVVideos(v) },
                { e -> handleNetworkError(e) }))
    }

    fun getTVCredits() {

        apiRequest.add(api.getTVCredit(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { c -> handleGetTVCredits(c) },
                { e -> handleNetworkError(e) }))
    }

    private fun handleGetTVVideos(videos: MovieVideosResponse) {
        this.videos.value = videos
    }

    private fun handleGetTVCredits(credits: CreditResponse) {
        this.credits.value = credits
    }

    private fun handleNetworkError(e: Throwable) {
        Timber.e(e)
    }
}