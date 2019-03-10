package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditResponse(
    val id: Int?,
    val cast: List<Cast>?) : Parcelable