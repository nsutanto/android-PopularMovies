package com.nsutanto.popularmovies.utils

import com.nsutanto.popularmovies.BuildConfig

object AppConstants {

    const val BASE_URL_POSTER = "https://image.tmdb.org/t/p/w185"
    const val BASE_URL_POSTER_MEDIUM = "https://image.tmdb.org/t/p/w342"
    const val BASE_URL_POSTER_BIG = "https://image.tmdb.org/t/p/w500"
    const val BASE_URL_BACKDROP_MEDIUM = "https://image.tmdb.org/t/p/h632"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val YOUTUBE_URL = "https://www.youtube.com/watch?v="
    const val YOUTUBE_THUMBNAIL_PRE = "https://img.youtube.com/vi/"
    const val YOUTUBE_THUMBNAIL_POST = "/0.jpg"
    const val API_KEY = "api_key"
    const val API_KEY_VALUE = BuildConfig.API_KEY

    const val INVALID_ACTIVITY = "Invalid Activity"


    const val MOVIE_INTENT = ".movie"
    const val MOVIE_LIST_INTENT = ".movie_list"
    const val TV_INTENT = ".tv"
    const val TV_LIST_INTENT = ".tv_list"
    const val ALL_ITEM_TYPE_INTENT = ".allItemType"

    var POPULAR_MOVIE_PAGE = 1
    var TOP_RATED_MOVIE_PAGE = 1
    var POPULAR_TV_PAGE = 1
    var TOP_RATED_TV_PAGE = 1

    enum class AllItemType(val value: Int) {
        POPULAR_MOVIE(0),
        TOP_RATED_MOVIE(1),
        POPULAR_TV(2),
        TOP_RATED_TV(3)
    }
}