package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.data.model.Review
import com.nsutanto.popularmovies.data.model.Video
import io.reactivex.Observable

interface ApiSource {

    fun getPopularMovies(): Observable<MovieResponse>

    fun getTopRatedMovies(): Observable<MovieResponse>

    fun getMovieVideos(id: String): Observable<Video>

    fun getMovieReviews(id: String): Observable<Review>

    fun getPopularTV(): Observable<MovieResponse>

    fun getTopRatedTV(): Observable<MovieResponse>

    fun getTVVideos(id: String): Observable<Video>

    fun getTVReviews(id: String): Observable<Review>

}
