package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(val castId: Int?,
                val character: String?,
                val creditId: String?,
                val name: String?,
                val profilePath: String?) : Parcelable
