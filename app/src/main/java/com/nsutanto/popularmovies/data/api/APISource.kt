package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.PopularMovieResult
import io.reactivex.Observable

interface ApiSource {

    fun getPopularMovies(): Observable<PopularMovieResult>
}
