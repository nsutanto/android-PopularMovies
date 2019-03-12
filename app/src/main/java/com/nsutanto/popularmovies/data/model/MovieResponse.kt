package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    val page: Int?,
    val totalResults: Int?,
    val totalPages: Int?,
    val results: List<Movie>?) : Parcelable
