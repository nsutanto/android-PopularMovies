package com.nsutanto.popularmovies.data.model

import android.os.Parcelable
import com.nsutanto.popularmovies.utils.AppConstants.YOUTUBE_THUMBNAIL_POST
import com.nsutanto.popularmovies.utils.AppConstants.YOUTUBE_THUMBNAIL_PRE
import com.nsutanto.popularmovies.utils.AppConstants.YOUTUBE_URL
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(val id: String?,
                 val name: String?,
                 val type: String?,
                 val key: String?) : Parcelable {

    fun getYouTubeURL(): String {
        return YOUTUBE_URL + key
    }

    fun getYouTubeThumbnailURL(): String {
        return YOUTUBE_THUMBNAIL_PRE + key + YOUTUBE_THUMBNAIL_POST
    }
}
