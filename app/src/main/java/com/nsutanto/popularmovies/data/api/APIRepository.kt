package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class APIRepository
@Inject
constructor(private val apiFactory: APIFactory) : ApiSource {

    private var tmdbService = apiFactory.createTMDBService()

    override fun getPopularMovies(page: Int): Observable<MovieResponse> {

        return tmdbService.getPopularMovie(page)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTopRatedMovies(page: Int): Observable<MovieResponse> {
        return tmdbService.getTopRatedMovie(page)
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

    override fun getPopularTV(page: Int): Observable<TVResponse> {
        return tmdbService.getPopularTV(page)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTopRatedTV(page: Int): Observable<TVResponse> {
        return tmdbService.getTopRatedTV(page)
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