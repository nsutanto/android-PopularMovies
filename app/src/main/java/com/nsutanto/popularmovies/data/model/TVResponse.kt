package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVResponse(
    val page: Int?,
    val totalResults: Int?,
    val totalPages: Int?,
    val results: List<TV>?) : Parcelable