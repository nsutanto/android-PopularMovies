package com.nsutanto.popularmovies.ui.main

import com.nsutanto.popularmovies.data.model.Movie

interface MainActivityListener {

    fun onPopularMovieUpdate(movies: List<Movie>)
}