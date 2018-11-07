package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.Review
import com.nsutanto.popularmovies.data.model.Video
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface tmdb {

    @GET("movie/popular")
    fun getPopularMovie(): Observable<Movie>

    @GET("movie/top_rated")
    fun getTopRatedMovie(): Observable<Movie>

    @GET("movie/{id}/videos")
    fun getVideos(@Path("id") movieID: String): Observable<Video>

    @GET("movie/{id}/reviews")
    fun getReviews(@Path("id") movieID: String): Observable<Review>
}