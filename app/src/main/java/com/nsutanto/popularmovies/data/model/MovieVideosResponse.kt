package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieVideosResponse(
    val id: Int?,
    val results: List<Video>?) : Parcelable