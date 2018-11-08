package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.Movie
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class APIRepository
constructor(private val apiFactory: APIFactory) {

    fun getPopularMovies(): Observable<Movie> {
        val tmdbService = apiFactory.createTMDBService()

        return tmdbService.getPopularMovie()
            .subscribeOn(Schedulers.newThread())
    }
}