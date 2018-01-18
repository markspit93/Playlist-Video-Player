package com.playlist.video.player.data.model

import com.google.gson.annotations.SerializedName

data class Video(

        @SerializedName("video_url")
        val videoUrl: String,

        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,

        val title: String,

        val duration: Long
)
