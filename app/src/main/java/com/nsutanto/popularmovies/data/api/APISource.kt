package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.MovieResponse
import io.reactivex.Observable

interface ApiSource {

    fun getPopularMovies(): Observable<MovieResponse>
}
