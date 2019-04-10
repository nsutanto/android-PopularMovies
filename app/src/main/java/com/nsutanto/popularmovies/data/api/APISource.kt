package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.*
import io.reactivex.Observable

interface ApiSource {

    fun getPopularMovies(page: Int): Observable<MovieResponse>

    fun getTopRatedMovies(page: Int): Observable<MovieResponse>

    fun getMovieVideos(id: Int): Observable<MovieVideosResponse>

    fun getMovieReviews(id: Int): Observable<Review>

    fun getMovieCredit(id: Int): Observable<CreditResponse>

    fun getPopularTV(page: Int): Observable<TVResponse>

    fun getTopRatedTV(page: Int): Observable<TVResponse>

    fun getTVVideos(id: Int): Observable<MovieVideosResponse>

    fun getTVReviews(id: Int): Observable<Review>

    fun getTVCredit(id: Int): Observable<CreditResponse>

}
