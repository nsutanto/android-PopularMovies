package com.nsutanto.popularmovies.data.api

import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.data.model.Review
import com.nsutanto.popularmovies.data.model.Video
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

    override fun getMovieVideos(id: String): Observable<Video> {
       return tmdbService.getMovieVideos(id)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getMovieReviews(id: String): Observable<Review> {
        return tmdbService.getMovieReviews(id)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getPopularTV(): Observable<MovieResponse> {
        return tmdbService.getPopularTV()
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTopRatedTV(): Observable<MovieResponse> {
        return tmdbService.getTopRatedTV()
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTVVideos(id: String): Observable<Video> {
        return tmdbService.getTVVideos(id)
            .subscribeOn(Schedulers.newThread())
    }

    override fun getTVReviews(id: String): Observable<Review> {
        return tmdbService.getTVReviews(id)
            .subscribeOn(Schedulers.newThread())
    }
}