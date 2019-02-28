package com.nsutanto.popularmovies.ui.main

import com.nsutanto.popularmovies.data.model.Movie

interface MainActivityListener {

    fun displayPopularMovies(movies: List<Movie>)
}