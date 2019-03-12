package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TV(val id: Int?,
                 val name: String?,
                 val posterPath: String?,
                 val backdropPath: String?,
                 val voteAverage: String?,
                 val overview: String?,
                 val favorite: Int?) : Parcelable