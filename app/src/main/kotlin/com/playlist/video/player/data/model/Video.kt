package com.playlist.video.player.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Video(

    @SerializedName("video_url")
    val videoUrl: String,

    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,

    val title: String,

    val duration: Long

) : Parcelable {

    // region Parcelable Implementation

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
            override fun createFromParcel(source: Parcel): Video = Video(source)
            override fun newArray(size: Int): Array<Video?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readLong()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
            writeString(videoUrl)
            writeString(thumbnailUrl)
            writeString(title)
            writeLong(duration)
    }

    override fun describeContents() = 0

    // endregion
}
