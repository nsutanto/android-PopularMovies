package com.nsutanto.popularmovies.data.model

data class MovieResponse(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<Movie>)
