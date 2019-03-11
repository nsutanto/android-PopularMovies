package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class APIRepository
@Inject
constructor(private val apiFactory: APIFactory) : ApiSource {

    private var tmdbService = apiFactory.createTMDBService()

    override fun getPopularMovies(): Observable<MovieResponse> {

        return tmdbService.getPopularMovie()
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTopRatedMovies(): Observable<MovieResponse> {
        return tmdbService.getTopRatedMovie()
            .subscribeOn(Schedulers.newThread())
    }

    override fun getMovieVideos(id: Int): Observable<MovieVideosResponse> {
       return tmdbService.getMovieVideos(id)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getMovieReviews(id: Int): Observable<Review> {
        return tmdbService.getMovieReviews(id)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getMovieCredit(id: Int): Observable<CreditResponse> {
        return tmdbService.getMovieCredit(id)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getPopularTV(): Observable<TVResponse> {
        return tmdbService.getPopularTV()
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTopRatedTV(): Observable<TVResponse> {
        return tmdbService.getTopRatedTV()
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTVVideos(id: Int): Observable<MovieVideosResponse> {
        return tmdbService.getTVVideos(id)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTVReviews(id: Int): Observable<Review> {
        return tmdbService.getTVReviews(id)
            .subscribeOn(Schedulers.newThread())
    }
}