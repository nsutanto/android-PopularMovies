package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.data.model.Review
import com.nsutanto.popularmovies.data.model.Video
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBService {

    // Movie
    @GET("movie/popular")
    fun getPopularMovie(): Observable<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(): Observable<MovieResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: String): Observable<Video>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: String): Observable<Review>

    // TV
    @GET("tv/popular")
    fun getPopularTV(): Observable<MovieResponse>

    @GET("tv/top_rated")
    fun getTopRatedTV(): Observable<MovieResponse>

    @GET("tv/{id}/videos")
    fun getTVVideos(@Path("id") id: String): Observable<Video>

    @GET("tv/{id}/reviews")
    fun getTVReviews(@Path("id") id: String): Observable<Review>
}