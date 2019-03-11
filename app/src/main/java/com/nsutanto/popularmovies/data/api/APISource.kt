package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.*
import io.reactivex.Observable

interface ApiSource {

    fun getPopularMovies(): Observable<MovieResponse>

    fun getTopRatedMovies(): Observable<MovieResponse>

    fun getMovieVideos(id: Int): Observable<MovieVideosResponse>

    fun getMovieReviews(id: Int): Observable<Review>

    fun getMovieCredit(id: Int): Observable<CreditResponse>

    fun getPopularTV(): Observable<TVResponse>

    fun getTopRatedTV(): Observable<TVResponse>

    fun getTVVideos(id: Int): Observable<MovieVideosResponse>

    fun getTVReviews(id: Int): Observable<Review>

}
