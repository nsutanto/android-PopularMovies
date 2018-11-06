package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val id: Int,
                 val title: String,
                 val releaseDate: String,
                 val posterPath: String,
                 val voteAverage: String,
                 val overview: String,
                 val favorite: Int) : Parcelable

