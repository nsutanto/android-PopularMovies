package com.nsutanto.popularmovies.ui.main

import com.nsutanto.popularmovies.data.api.ApiSource
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.viewmodels.MovieViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MainPresenter
@Inject
constructor(private val view: MainContract.View,
            private val api: ApiSource)
    : MainContract.Presenter {

    private var apiRequest = CompositeDisposable()

    override fun start() {
        getPopularMovies()
    }

    override fun stop() {
        apiRequest.clear()
    }

    override fun onMovieTabClicked() {
        view.displayMovieFragment()
    }

    override fun onTVTabClicked() {
        view.displayTVFragment()
    }

    private fun getPopularMovies() {
        apiRequest.add(api.getPopularMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { m -> handleGetPopularMovies(m) },
                { e -> handleNetworkError(e) }))
    }

    private fun handleGetPopularMovies(movieResponse: MovieResponse) {
        view.displayPopularMovies(movieResponse.results)
    }

    private fun handleNetworkError(e: Throwable) {
        Timber.e(e)
    }
}