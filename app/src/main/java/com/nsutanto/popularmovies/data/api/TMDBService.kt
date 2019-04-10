package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    // Movie
    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page: Int): Observable<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(@Query("page")page: Int): Observable<MovieResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: Int): Observable<MovieVideosResponse>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: Int): Observable<Review>

    @GET("movie/{id}/credits")
    fun getMovieCredit(@Path("id") id: Int): Observable<CreditResponse>

    // TV
    @GET("tv/popular")
    fun getPopularTV(@Query("page")page: Int): Observable<TVResponse>

    @GET("tv/top_rated")
    fun getTopRatedTV(@Query("page")page: Int): Observable<TVResponse>

    @GET("tv/{id}/videos")
    fun getTVVideos(@Path("id") id: Int): Observable<MovieVideosResponse>

    @GET("tv/{id}/reviews")
    fun getTVReviews(@Path("id") id: Int): Observable<Review>

    @GET("tv/{id}/credits")
    fun getTVCredit(@Path("id") id: Int): Observable<CreditResponse>

}