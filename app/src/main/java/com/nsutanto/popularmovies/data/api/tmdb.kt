package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.Movie
import retrofit2.http.GET
import rx.Observable

interface tmdb {

    @GET("movie/popular")
    fun getPopularMovie(): Observable<Movie>

    @GET("movie/top_rated")
    fun getTopRatedMovie(): Observable<Movie>
}