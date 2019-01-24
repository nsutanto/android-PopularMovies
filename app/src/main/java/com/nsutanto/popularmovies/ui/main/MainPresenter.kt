package com.nsutanto.popularmovies.ui.main

import com.nsutanto.popularmovies.data.api.ApiSource
import com.nsutanto.popularmovies.data.model.PopularMovieResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MainPresenter
@Inject
constructor(private val view: MainContract.View,
            private val api: ApiSource
) : MainContract.Presenter {

    private var apiRequest = CompositeDisposable()

    override fun start() {

        apiRequest.add(api.getPopularMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies -> handleGetPopularMovies(movies) }, { e -> handleNetworkError(e) }))
    }

    override fun stop() {
        apiRequest.clear()
    }

    private fun handleGetPopularMovies(movies: PopularMovieResult) {
    }

    private fun handleNetworkError(e: Throwable) {
        Timber.e(e)
    }
}