package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.*
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
    fun getMovieVideos(@Path("id") id: Int): Observable<MovieVideosResponse>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: Int): Observable<Review>

    @GET("movie/{id}/credits")
    fun getMovieCredit(@Path("id") id: Int): Observable<CreditResponse>

    // TV
    @GET("tv/popular")
    fun getPopularTV(): Observable<TVResponse>

    @GET("tv/top_rated")
    fun getTopRatedTV(): Observable<TVResponse>

    @GET("tv/{id}/videos")
    fun getTVVideos(@Path("id") id: Int): Observable<MovieVideosResponse>

    @GET("tv/{id}/reviews")
    fun getTVReviews(@Path("id") id: Int): Observable<Review>

}