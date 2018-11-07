package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import com.nsutanto.popularmovies.utils.AppConstants.YOUTUBE_THUMBNAIL_POST
import com.nsutanto.popularmovies.utils.AppConstants.YOUTUBE_THUMBNAIL_PRE
import com.nsutanto.popularmovies.utils.AppConstants.YOUTUBE_URL
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(val id: String) : Parcelable {

    fun getYouTubeURL(): String {
        return YOUTUBE_URL + id
    }

    fun getYouTubeThumbnailURL(): String {
        return YOUTUBE_THUMBNAIL_PRE + id + YOUTUBE_THUMBNAIL_POST
    }
}
